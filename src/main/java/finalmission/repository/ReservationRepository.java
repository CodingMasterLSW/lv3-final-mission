package finalmission.repository;

import finalmission.domain.Reservation;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCrewId(Long crewId);

    List<Reservation> findAllByCoachId(Long coachId);

    void deleteByCoachIdAndReservationTime(Long coachId, LocalDateTime reservationTime);
}
