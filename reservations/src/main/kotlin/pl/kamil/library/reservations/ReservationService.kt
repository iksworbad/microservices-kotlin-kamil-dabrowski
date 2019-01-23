package pl.kamil.library.reservations

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.lang.IllegalStateException
import java.time.LocalDateTime


@Service
class ReservationService(
        private val reservationRepository: ReservationRepository,
        private val userRepository: UserRepository,
        private val restTemplate: RestTemplate,
        @Value("\${sc.book-service-url}") private val bookServiceUrl: String
) {
    private fun mappedReservations(block: () -> List<Reservation>) = block().map {

        val book = try {
            restTemplate.getForEntity("$bookServiceUrl${it.bookId}",
                    BookDTO::class.java).body
        } catch (e: RestClientException) {
            BookDTO()
        }
        ReservationWithBookDTO(it.toDTO(), book)
    }


    fun getAllReservations() = mappedReservations {
        reservationRepository.findAll()
    }

    fun getUserReservations(userId: Long) = mappedReservations {
        reservationRepository.findAllByUser_Id(userId)
    }

    fun getBookReservations(bookId: Long) = mappedReservations {
        reservationRepository.findAllByBookId(bookId)
    }

    private fun mappedReservation (block: () -> Reservation) = mappedReservations { listOf(block()) }.first()

    fun makeReservation(userId: Long,bookId: Long, days: Long) = mappedReservation {
        with(userRepository
                .findById(userId)){
            when {
                this.isPresent -> {
                    val bookReservations = reservationRepository.findAllByUser_Id(bookId)
                    when {
                        mayBeReserved(bookReservations, bookId) -> reservationRepository.save(Reservation(0,
                                LocalDateTime.now(),
                                LocalDateTime.now().plusDays(days),
                                bookId,
                                this.get()))
                        else -> throw IllegalStateException("Book with id $bookId os already booked or s now available")
                    }
                }
                else -> throw IllegalStateException("User with id $userId does not exist")
            }
        }
    }

    private fun mayBeReserved(bookReservations: List<Reservation>, bookId: Long) =
        (bookReservations.isEmpty() || bookReservations.last().toTime.isBefore(LocalDateTime.now())) && isBookAvailable(bookId)

    private fun isBookAvailable(bookId: Long) = try {
        restTemplate.getForEntity("$bookServiceUrl/is-available/$bookId",
                Boolean::class.java
        ).body ?: false
    }catch (e: RestClientException){
        false
    }


}
