package finalmission.service;

import static org.assertj.core.api.Assertions.assertThat;

import finalmission.domain.Reservation;
import finalmission.dto.ReservationRequestDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("INSERT INTO CREW(name, email) VALUES('젠슨','a@com')");
        jdbcTemplate.execute("INSERT INTO COACH(name, email) VALUES('솔라','b@com')");
    }

    @AfterEach
    void clear() {
        jdbcTemplate.execute("DROP TABLE RESERVATION");
        jdbcTemplate.execute("DROP TABLE CREW");
        jdbcTemplate.execute("DROP TABLE COACH");
    }

    @Test
    @DisplayName("예약을 저장할 수 있어야 한다.")
    void save_reservation() {
        ReservationRequestDto requestDto = new ReservationRequestDto(
            1L, 1L, LocalDateTime.now().plusDays(1)
        );
        Reservation save = reservationService.save(requestDto);
        assertThat(save.getUser().getName()).isEqualTo("젠슨");
        assertThat(save.getCoach().getName()).isEqualTo("솔라");

    }
}
