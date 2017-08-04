package tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by acer on 2017/7/31.
 */
public class GetPropertyUtil {

    public static String getFileAddress(String fileType) throws IOException {
        InputStream inStream = new GetPropertyUtil().getInputStream();
        Properties pro = new Properties();
        pro.load(inStream);
        String s = pro.getProperty(fileType);
        inStream.close();
        return s;
    }

    public InputStream getInputStream(){
        return this.getClass().getClassLoader().getResourceAsStream("fileAddress.properties");
    }

    public static void main(String agrs[]) throws IOException {
        System.out.println(GetPropertyUtil.getFileAddress("Files"));
    }

}
