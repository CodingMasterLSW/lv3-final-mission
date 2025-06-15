package finalmission.service;

import finalmission.domain.Member;
import finalmission.login.JwtProvider;
import finalmission.login.LoginRequestDto;
import finalmission.login.Token;
import finalmission.repository.CoachRepository;
import finalmission.repository.CrewRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final JwtProvider jwtProvider;
    private final CrewRepository crewRepository;
    private final CoachRepository coachRepository;

    public LoginService(
        JwtProvider jwtProvider,
        CrewRepository crewRepository,
        CoachRepository coachRepository
    ) {
        this.jwtProvider = jwtProvider;
        this.crewRepository = crewRepository;
        this.coachRepository = coachRepository;
    }

    public Token coachLogin(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.email();
        Member coach = findCoachByEmail(email);
        return jwtProvider.createToken(coach);
    }

    public Token crewLogin(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.email();
        Member crew = findCrewByEmail(email);
        return jwtProvider.createToken(crew);
    }

    private Member findCoachByEmail(String email) {
        return coachRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메일입니다."));
    }

    private Member findCrewByEmail(String email) {
        return crewRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메일입니다."));
    }
}
