package finalmission.controller;

import finalmission.domain.Reservation;
import finalmission.dto.ReservationRequestDto;
import finalmission.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public Reservation save(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.save(reservationRequestDto);
    }
}
