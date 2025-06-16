package finalmission.common.handler;

import finalmission.domain.login.JwtProvider;
import finalmission.domain.login.MemberType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CoachAuthorizeInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    public CoachAuthorizeInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String token = request.getHeader("token");
        Claims claims = jwtProvider.getClaimsAndValidateToken(token);
        MemberType memberType = (MemberType) claims.get("memberType");
        if (!memberType.name().equals("COACH")) {
            throw new IllegalStateException("코치만 접근 가능합니다.");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);

    }
}
