package finalmission.dto;

import java.time.LocalDate;

public record ReservationRequestDto(
    Long crewId,
    Long coachId,
    Long reservationTimeId,
    LocalDate date
) {
}
