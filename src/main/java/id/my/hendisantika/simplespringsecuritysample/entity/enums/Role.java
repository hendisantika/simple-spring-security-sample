package id.my.hendisantika.simplespringsecuritysample.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/10/24
 * Time: 06.00
 * To change this template use File | Settings | File Templates.
 */
public enum Role {
    USER,
    ADMIN,
    CUSTOMER;


    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
