package finalmission.controller;

import finalmission.domain.Reservation;
import finalmission.dto.AcceptResultDto;
import finalmission.dto.ReservationRemoveRequest;
import finalmission.dto.ReservationRequestDto;
import finalmission.dto.ReservationResponse;
import finalmission.service.ReservationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


// TODO 1순위 : Crew와 Coach의 API 재사용 방법 생각해보기
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // TODO : API Endpoint 변경하기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reservations")
    public Reservation save(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.save(reservationRequestDto);
    }

    // TODO : API Endpoint 변경하기
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/reservations/coach/{reservationId}")
    public void deleteFromCoach(
        @PathVariable Long reservationId,
        @RequestBody ReservationRemoveRequest request
    ) {
        reservationService.deleteFromCoach(reservationId, request.id());
    }

    // TODO : API Endpoint 변경하기
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/reservations/crew/{reservationId}")
    public void deleteFromCrew(
        @PathVariable Long reservationId,
        @RequestBody ReservationRemoveRequest request) {
        reservationService.deleteFromCrew(reservationId, request.id());
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

    @PostMapping("/reservations/{reservationId}/accept")
    public void acceptReservation(@PathVariable Long reservationId, @RequestBody AcceptResultDto resultDto) {
        reservationService.accept(reservationId, resultDto);
    }
}
