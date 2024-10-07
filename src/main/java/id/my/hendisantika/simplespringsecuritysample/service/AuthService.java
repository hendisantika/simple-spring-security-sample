package id.my.hendisantika.simplespringsecuritysample.service;

import id.my.hendisantika.simplespringsecuritysample.entity.JWTAuthResponse;
import id.my.hendisantika.simplespringsecuritysample.entity.LoginDto;
import id.my.hendisantika.simplespringsecuritysample.jwt.JwtTokenProvider;
import id.my.hendisantika.simplespringsecuritysample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/10/24
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public JWTAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        JWTAuthResponse bearer = JWTAuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getJwtExpirationDate())
                .build();

        return bearer;
    }
}
