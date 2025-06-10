package finalmission.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("spqjekdl1004@naver.com");
        simpleMailMessage.setSubject("커피챗이 예약되었습니다.");
        simpleMailMessage.setText("커피챗에 대한 세부적인 내용 추가하기");
        javaMailSender.send(simpleMailMessage);
    }
}
