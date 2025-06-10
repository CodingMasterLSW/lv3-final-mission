package finalmission.service;

import finalmission.domain.Coach;
import finalmission.domain.Reservation;
import finalmission.domain.Crew;
import finalmission.dto.ReservationRequestDto;
import finalmission.repository.CoachRepository;
import finalmission.repository.ReservationRepository;
import finalmission.repository.CrewRepository;
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

    private Coach findCoachById(Long id) {
        return coachRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코치입니다."));
    }

    private Crew findCrewById(Long id) {
        return crewRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

}
