package finalmission.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import finalmission.domain.Reservation;
import java.time.LocalDateTime;

public record ReservationResponse(
    Long reservationId,
    String crewName,
    String coachName,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime reservationTime
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
            reservation.getId(),
            reservation.getCrew().getName(),
            reservation.getCoach().getName(),
            reservation.getReservationTime()
        );
    }

}