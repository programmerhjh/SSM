package tool;

/**
 * 用于发送短信的一个常量类
 * Created by acer on 2017/7/8.
 */
public class MessageConfig {
    /**
     * url前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 开发者注册后系统自动生成的账号，可在官网登录后查看
     */
    public static final String ACCOUNT_SID = "14476ad6c5b4416e89b14ee86a305653";

    /**
     * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
     */
    public static final String AUTH_TOKEN = "accda94c1a3d44bfb9ec6209fd251e57";

    /**
     * 响应数据类型, JSON或XML
     */
    public static final String RESP_DATA_TYPE = "json";
}
