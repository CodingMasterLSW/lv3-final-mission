package finalmission.dto;


import finalmission.domain.Reservation;

public record MailRequestDto(String sendTo, String subject, String text) {

    private static final String SEND_TO_COACH_SUBJECT_MESSAGE = "%s(으)로부터 커피챗 예약이 도착했습니다.";
    private static final String SEND_TO_COACH_TEXT_MESSAGE = """
        %s %s에 %s로부터 커피챗 예약이 도착했습니다.
        애플리케이션에 접속해 수락/거절 버튼을 눌러주세요.
        """;

    public static MailRequestDto toCoach(Reservation reservation) {
        return new MailRequestDto(
            reservation.getCoach().getEmail(),
            String.format(SEND_TO_COACH_SUBJECT_MESSAGE, reservation.getCrew().getName()),
            String.format(SEND_TO_COACH_TEXT_MESSAGE,
                reservation.getDate().toString(),
                reservation.getReservationTime().getStartAt(),
                reservation.getCrew().getName())
        );
    }

}
