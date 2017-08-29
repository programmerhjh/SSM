package tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by acer on 2017/7/31.
 */
public class GetPropertyUtil {

    public static String getFileAddress(String fileType) throws IOException {
        InputStream inStream = new GetPropertyUtil().getFileAddressInputStream();
        Properties pro = new Properties();
        pro.load(inStream);
        String result = pro.getProperty(fileType);
        inStream.close();
        return result;
    }

    public InputStream getFileAddressInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream("fileAddress.properties");
    }

    public static String getWebsiteAddress(String websiteAddress) throws IOException {
        InputStream inStream = new GetPropertyUtil().getWebsiteAddressInputStream();
        Properties pro = new Properties();
        pro.load(inStream);
        String result = pro.getProperty(websiteAddress);
        return result;
    }

    public InputStream getWebsiteAddressInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream("websiteAddress.properties");
    }



}
