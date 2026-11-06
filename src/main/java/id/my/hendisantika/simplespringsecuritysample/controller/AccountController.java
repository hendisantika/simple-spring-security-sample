package id.my.hendisantika.simplespringsecuritysample.controller;

import id.my.hendisantika.simplespringsecuritysample.entity.Customer;
import id.my.hendisantika.simplespringsecuritysample.entity.LoginDto;
import id.my.hendisantika.simplespringsecuritysample.entity.enums.Role;
import id.my.hendisantika.simplespringsecuritysample.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 07/11/26
 * Time: 05.48
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Map<String, Object>> register(@RequestBody LoginDto request) {
        if (customerService.findByEmailOptional(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Account already exists"));
        }

        Customer customer = Customer.builder()
                .email(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();
        Customer saved = customerService.save(customer);

        return ResponseEntity.ok(Map.of("id", saved.getId(), "email", saved.getEmail()));
    }
}
