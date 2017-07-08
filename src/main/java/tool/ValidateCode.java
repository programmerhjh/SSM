package tool;

/**
 * Created by acer on 2017/7/8.
 */
public class ValidateCode {
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}
