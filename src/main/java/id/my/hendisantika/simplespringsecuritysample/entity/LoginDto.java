package id.my.hendisantika.simplespringsecuritysample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : simple-spring-security-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/10/24
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginDto {

    private String username;
    private String password;
}
