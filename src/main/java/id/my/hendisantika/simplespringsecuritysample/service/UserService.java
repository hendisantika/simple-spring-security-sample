package id.my.hendisantika.simplespringsecuritysample.service;

import id.my.hendisantika.simplespringsecuritysample.entity.User;
import id.my.hendisantika.simplespringsecuritysample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/10/24
 * Time: 06.05
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public <S extends User> S save(S user) {
        return userRepository.save(user);
    }
}
