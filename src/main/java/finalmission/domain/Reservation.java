package finalmission.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    private Crew crew;

    private LocalDateTime reservationTime;

    protected Reservation() {}

    public Reservation(Long id, Coach coach, Crew crew, LocalDateTime reservationTime) {
        this.id = id;
        this.coach = coach;
        this.crew = crew;
        this.reservationTime = reservationTime;
    }

    public Reservation(Coach coach, Crew crew, LocalDateTime reservationTime) {
        this.coach = coach;
        this.crew = crew;
        this.reservationTime = reservationTime;
    }

    public boolean isOwnerCrewRequest(Long crewId) {
        return this.crew.getId().equals(crewId);
    }

    public boolean isOwnerCoachRequest(Long coachId) {
        return this.coach.getId().equals(coachId);
    }

    public Long getId() {
        return id;
    }

    public Coach getCoach() {
        return coach;
    }

    public Crew getCrew() {
        return crew;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
