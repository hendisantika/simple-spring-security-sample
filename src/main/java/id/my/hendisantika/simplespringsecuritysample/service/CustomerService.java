package id.my.hendisantika.simplespringsecuritysample.service;

import id.my.hendisantika.simplespringsecuritysample.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/10/24
 * Time: 06.04
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
}
