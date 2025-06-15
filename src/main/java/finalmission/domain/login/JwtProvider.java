package finalmission.domain.login;

import finalmission.domain.member.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.access.expire-length}")
    private long accessTokenExpireTime;

    public Token createToken(Member member) {
        Date date = new Date();
        Date accessTokenExpiredAt = new Date(date.getTime() + accessTokenExpireTime);
        String accessToken = Jwts.builder()
            .claim("id", member.getId())
            .claim("memberType", member.getType())
            .issuedAt(date)
            .expiration(accessTokenExpiredAt)
            .signWith(convertFrom(secretKey), SIG.HS256)
            .compact();
        return new Token(accessToken);
    }

    private SecretKey convertFrom(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
