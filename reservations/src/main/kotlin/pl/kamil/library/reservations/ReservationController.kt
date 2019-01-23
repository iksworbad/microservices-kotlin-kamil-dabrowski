package pl.kamil.library.reservations

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/reservations")
class ReservationController(
        private val reservationService: ReservationService
) {
    @GetMapping("/")
    fun getAllreservations() = reservationService.getAllReservations()

    @GetMapping("/user/{userId}")
    fun getUserReservations(@PathVariable userId: Long) = reservationService.getUserReservations(userId)

    @GetMapping("/book/{bookId}")
    fun getBookReservations(@PathVariable bookId: Long) = reservationService.getBookReservations(bookId)

    @PostMapping("/new")
    fun makeReservation(@RequestBody newReservationDTO: NewReservationDTO) =
            reservationService.makeReservation(
                    newReservationDTO.userId,
                    newReservationDTO.bookId,
                    newReservationDTO.days
            )
}