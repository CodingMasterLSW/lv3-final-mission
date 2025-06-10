package finalmission.controller;

import finalmission.domain.Reservation;
import finalmission.dto.ReservationRequestDto;
import finalmission.service.ReservationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/reservations/{crewId}")
    public List<Reservation> getAllFromCrew(@PathVariable("crewId") Long crewId) {
        return reservationService.getAllReservationsFromCrewId(crewId);
    }


    @GetMapping("/reservations/{coachId}")
    public List<Reservation> getAllFromCoach(@PathVariable("coachId") Long coachId) {
        return reservationService.getAllReservationsFromCoachId(coachId);
    }
}
