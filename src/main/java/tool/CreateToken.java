package tool;

import java.util.UUID;

/**
 * Created by acer on 2017/7/10.
 */
public class CreateToken {
    //生成登陆令牌 -- SSO登陆 (单点登录不做了，此工具作废)
    public static String createToken() {
        return UUID.randomUUID().toString();
    }

}
