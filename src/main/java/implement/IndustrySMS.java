package implement;

import tool.HttpUtil;
import tool.MessageConfig;
import tool.ValidateCode;

/**
 * 验证码通知短信接口
 *
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
    private static String operation = "/industrySMS/sendSMS";
    private static String accountSid = MessageConfig.ACCOUNT_SID;
    private static String to = "15819696097";
    private static String validateNum = ValidateCode.createRandomVcode();
    private static String smsContent = "【百豪HJH】验证码：" + validateNum + "，打死都不要告诉别人哦！ --hjh";
    public static String getTo() {
        return to;
    }

    public static void setTo(String to) {
        IndustrySMS.to = to;
    }
    public static String getValidateNum() {
        return validateNum;
    }

    public static void setValidateNum(String validateNum) {
        IndustrySMS.validateNum = validateNum;
    }
    /**
     * 验证码通知短信
     */
    public static void execute()
    {
        String url = MessageConfig.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        System.out.println("验证码" + getValidateNum());
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.getProperty("line.separator")  + result);
    }
}
