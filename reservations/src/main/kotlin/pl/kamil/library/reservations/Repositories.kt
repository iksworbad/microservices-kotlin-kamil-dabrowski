package pl.kamil.library.reservations

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>

interface ReservationRepository: JpaRepository<Reservation, Long> {

    override fun findAll(): List<Reservation>

    fun findAllByUser_Id(id: Long): List<Reservation>

    fun findAllByBookId(bookId: Long): List<Reservation>
}