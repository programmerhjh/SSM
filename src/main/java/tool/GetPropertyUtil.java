package tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by acer on 2017/7/31.
 */
public class GetPropertyUtil {

    //根据文件类型获取文件地址
    public static String getFileAddress(String fileType) throws IOException {
        InputStream inStream = new GetPropertyUtil().getFileAddressInputStream();
        Properties pro = new Properties();
        pro.load(inStream);
        String result = pro.getProperty(fileType);
        inStream.close();
        return result;
    }


    //获取文件地址的输入流
    public InputStream getFileAddressInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream("fileAddress.properties");
    }

    //根据websiteAddress.properties获取不同资源的网址
    public static String getWebsiteAddress(String websiteAddress) throws IOException {
        InputStream inStream = new GetPropertyUtil().getWebsiteAddressInputStream();
        Properties pro = new Properties();
        pro.load(inStream);
        String result = pro.getProperty(websiteAddress);
        return result;
    }

    //获取资源网址的输入流
    public InputStream getWebsiteAddressInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream("websiteAddress.properties");
    }



}
