package id.my.hendisantika.simplespringsecuritysample.service;

import id.my.hendisantika.simplespringsecuritysample.entity.User;
import id.my.hendisantika.simplespringsecuritysample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("User with that id %d does not found", id)));
    }

    public Optional<? extends User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
