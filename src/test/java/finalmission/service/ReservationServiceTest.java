package finalmission.service;

import static org.assertj.core.api.Assertions.assertThat;

import finalmission.domain.Reservation;
import finalmission.dto.ReservationRequestDto;
import finalmission.dto.ReservationResponse;
import java.time.LocalDateTime;
import java.util.List;
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
        initData();
    }

    @AfterEach
    void clear() {
        jdbcTemplate.execute("DELETE FROM RESERVATION");
        jdbcTemplate.execute("DELETE FROM CREW");
        jdbcTemplate.execute("DELETE FROM COACH");
    }

    @Test
    @DisplayName("예약을 저장할 수 있어야 한다.")
    void save_reservation() {

        // given
        ReservationRequestDto requestDto = new ReservationRequestDto(
            1L, 1L, LocalDateTime.now().plusDays(1)
        );

        // when
        Reservation save = reservationService.save(requestDto);

        // then
        assertThat(save.getCrew().getName()).isEqualTo("젠슨");
        assertThat(save.getCoach().getName()).isEqualTo("솔라");
    }

    @Test
    @DisplayName("예약을 크루ID에 맞게 조회할 수 있어야 한다.")
    void find_all_reservations_by_crew_id() {

        // given
        ReservationRequestDto requestDto1 = new ReservationRequestDto(
            1L, 1L, LocalDateTime.now().plusDays(1)
        );
        ReservationRequestDto requestDto2 = new ReservationRequestDto(
            1L, 2L, LocalDateTime.now().plusDays(2)
        );

        ReservationRequestDto requestDto3 = new ReservationRequestDto(
            2L, 3L, LocalDateTime.now().plusDays(2)
        );

        reservationService.save(requestDto1);
        reservationService.save(requestDto2);
        reservationService.save(requestDto3);

        // when
        List<ReservationResponse> reservationResponses = reservationService.getAllReservationsFromCrewId(
            1L);

        // then
        assertThat(reservationResponses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("예약을 코치ID에 맞게 조회할 수 있어야 한다.")
    void find_all_reservations_by_coach_id() {

        // given
        ReservationRequestDto requestDto1 = new ReservationRequestDto(
            1L, 1L, LocalDateTime.now().plusDays(1)
        );
        ReservationRequestDto requestDto2 = new ReservationRequestDto(
            2L, 1L, LocalDateTime.now().plusDays(2)
        );

        ReservationRequestDto requestDto3 = new ReservationRequestDto(
            3L, 1L, LocalDateTime.now().plusDays(2)
        );

        reservationService.save(requestDto1);
        reservationService.save(requestDto2);
        reservationService.save(requestDto3);

        // when
        List<ReservationResponse> reservationResponses = reservationService.getAllReservationsFromCoachId(
            1L);

        // then
        assertThat(reservationResponses.size()).isEqualTo(3);
    }

    private void initData() {
        jdbcTemplate.execute("INSERT INTO CREW(id, name, email) VALUES(1L, '젠슨','a@com')");
        jdbcTemplate.execute("INSERT INTO CREW(id,name, email) VALUES(2L, '포포','b@com')");
        jdbcTemplate.execute("INSERT INTO CREW(id, name, email) VALUES(3L, '가이온','c@com')");
        jdbcTemplate.execute("INSERT INTO COACH(id, name, email) VALUES(1L, '솔라','a1@com')");
        jdbcTemplate.execute("INSERT INTO COACH(id, name, email) VALUES(2L, '리사','a2@com')");
        jdbcTemplate.execute("INSERT INTO COACH(id, name, email) VALUES(3L, '네오','a3@com')");
    }
}


