package finalmission.service;

import finalmission.domain.Coach;
import finalmission.domain.Reservation;
import finalmission.domain.Crew;
import finalmission.dto.ReservationRequestDto;
import finalmission.dto.ReservationResponse;
import finalmission.repository.CoachRepository;
import finalmission.repository.ReservationRepository;
import finalmission.repository.CrewRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final CoachRepository coachRepository;
    private final CrewRepository crewRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(
        CoachRepository coachRepository,
        CrewRepository crewRepository,
        ReservationRepository reservationRepository
    ) {
        this.coachRepository = coachRepository;
        this.crewRepository = crewRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation save(ReservationRequestDto request) {
        Coach coach = findCoachById(request.coachId());
        Crew crew = findCrewById(request.crewId());
        Reservation reservation = new Reservation(coach, crew, request.reservationTime());
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void deleteFromCrew(Long reservationId, Long crewId) {
        Reservation reservation = findReservationById(reservationId);
        if (!reservation.isOwnerCrewRequest(crewId)) {
            throw new IllegalStateException("본인의 예약만 삭제할 수 있습니다.");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Transactional
    public void deleteFromCoach(Long reservationId, Long coachId) {
        Reservation reservation = findReservationById(reservationId);
        if (!reservation.isOwnerCoachRequest(coachId)) {
            throw new IllegalStateException("본인의 예약만 삭제할 수 있습니다.");
        }
        reservationRepository.deleteById(reservationId);
    }

    // TODO : 메서드 재활용 해보기
    public List<ReservationResponse> getAllReservationsFromCrewId(Long crewId) {
        List<Reservation> reservations = reservationRepository.findAllByCrewId(crewId);
        return reservations.stream()
            .map(ReservationResponse::from)
            .toList();
    }

    public List<ReservationResponse> getAllReservationsFromCoachId(Long coachId) {
        List<Reservation> reservations = reservationRepository.findAllByCoachId(coachId);
        return reservations.stream()
            .map(ReservationResponse::from)
            .toList();
    }

    private Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다."));
    }

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코치입니다."));
    }

    private Crew findCrewById(Long id) {
        return crewRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

}
