package finalmission.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ReservationRequestDto(
    Long crewId,
    Long coachId,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime reservationTime) {

}
