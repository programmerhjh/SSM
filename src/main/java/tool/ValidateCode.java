package tool;

/**
 * 用于生成验证码的工具类
 * Created by acer on 2017/7/8.
 */
public class ValidateCode {

    /**
     * 随机生成验证码
     * @return
     * @time 2017年7月8日9:46:14
     */
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}
