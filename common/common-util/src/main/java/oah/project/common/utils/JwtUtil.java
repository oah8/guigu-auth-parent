/*
package oah.project.common.utils;




import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oah.project.model.system.User;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @ClassName JwtUtil
 * @Description TODO
 * @Author _oah
 * @Date 2023.09.27 19:42
 * @Version 1.0
 *//*

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    */
/**
     * 秘钥
     *//*

    private static final String SECRET = "my_secret";

    */
/**
     * 过期时间
     **//*

    private static final long EXPIRATION = 1800L;//单位为秒

    */
/**
     * 生成用户token,设置token超时时间
     *//*

    public  static String createToken(User user){
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token= JWT.create()
                .withHeader(map)                //添加头部
                //可以把数据存在claim中
                .withClaim("id",user.getId())      //userId
                .withClaim("userName",user.getUserName())
                .withClaim("password",user.getPassword())
                .withExpiresAt(expireDate)          //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        return token;
    }

    */
/**
     * 检验token并解析token
     *//*

    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt=null;
        try {
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt=verifier.verify(token);
        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("解析编码异常");
        }

        return jwt.getClaims();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setId(12);
        user.setUserName("oah");
        user.setPassword("123456");
        String token = JwtUtil.createToken(user);
        System.out.println(token);
    }
}

*/
