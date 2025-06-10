package finalmission.controller;

import finalmission.domain.Reservation;
import finalmission.dto.ReservationRequestDto;
import finalmission.dto.ReservationResponse;
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

    // TODO : API Endpoint 변경하기
    @GetMapping("/reservations/crew/{crewId}")
    public List<ReservationResponse> getAllFromCrew(@PathVariable("crewId") Long crewId) {
        return reservationService.getAllReservationsFromCrewId(crewId);
    }

    // TODO : API Endpoint 변경하기
    @GetMapping("/reservations/coach/{coachId}")
    public List<ReservationResponse> getAllFromCoach(@PathVariable Long coachId) {
        return reservationService.getAllReservationsFromCoachId(coachId);
    }
}
