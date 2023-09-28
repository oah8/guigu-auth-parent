package oah.project.system.custom;

import oah.project.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName CustomMd5Password
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.28 15:34
 * @Version 1.0
 */
// 自定义密码组件
@Component
public class CustomMd5Password implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
