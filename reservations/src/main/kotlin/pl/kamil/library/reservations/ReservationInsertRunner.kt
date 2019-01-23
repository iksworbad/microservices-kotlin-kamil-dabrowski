package pl.kamil.library.reservations

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class ReservationInsertRunner(
        private val reservationRepository: ReservationRepository,
        private val userRepository: UserRepository
): CommandLineRunner {

    override fun run(vararg args: String?) {

        val users = listOf(
                User(1, "Simple Coding"),
                User(2, "Jan Kowalski"),
                User(3, "Anna Bella")
        )

        userRepository.saveAll(users)

        val now = LocalDateTime.now()
        reservationRepository.saveAll(listOf(
                Reservation(0, now, now.plusDays(10), 1, users[0]),
                Reservation(0, now, now.plusDays(15), 2, users[1]),
                Reservation(0, now, now.plusDays(25), 3, users[2])
        ))
        println("""==========================================
            Users ${reservationRepository.count()}""".trimIndent())
    }
}