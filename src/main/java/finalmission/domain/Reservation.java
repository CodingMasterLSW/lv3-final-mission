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
    private User user;

    private LocalDateTime reservationTime;

    protected Reservation() {}

    public Reservation(Long id, Coach coach, User user, LocalDateTime reservationTime) {
        this.id = id;
        this.coach = coach;
        this.user = user;
        this.reservationTime = reservationTime;
    }

    public Reservation(Coach coach, User user, LocalDateTime reservationTime) {
        this.coach = coach;
        this.user = user;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public Coach getCoach() {
        return coach;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
