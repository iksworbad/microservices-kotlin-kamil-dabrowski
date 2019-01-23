package pl.kamil.library.reservations

import java.awt.print.Book
import java.time.LocalDateTime
import java.time.Year
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "RESERVATIONS")
class Reservation(
        @Id @GeneratedValue val id: Long = 0,
        @Column val fromTime: LocalDateTime = LocalDateTime.now(),
        @Column val toTime: LocalDateTime = LocalDateTime.now().plusDays(7),
        @Column val bookId: Long = 0,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User = User()
) {
    fun toDTO() = ReservationDTO(user.fullName, bookId, toTime)
}

@Entity
@Table(name = "USERS")
class User(
        @Id @GeneratedValue val id: Long = 0,
        @Column @NotNull @NotEmpty val fullName: String = ""
)

data class ReservationDTO(
        val userName: String = "",
        val bookId: Long = 0,
        val to: LocalDateTime = LocalDateTime.now().plusDays(7)
)

data class BookDTO(
        val title: String = "",
        val author: String = "",
        val releaseYear: Int = 0
)

data class ReservationWithBookDTO(
        val reservation: ReservationDTO,
        val book: BookDTO?
)

data class NewReservationDTO(
        val userId: Long,
        val bookId: Long,
        val days: Long
)