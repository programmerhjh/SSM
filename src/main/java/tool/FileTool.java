package tool;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 文件工具
 * @time 2017年7月30日9:37:14
 */
public class FileTool {
    public static ArrayList<String> fileList = new ArrayList<String>();
    public static ArrayList<Long> fileSizeList = new ArrayList<Long>();
    public static ArrayList<String> fileTimeList = new ArrayList<String>();
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /*
     * 得到某一路径下所有的目录文件
     */


    /**
     * 获取目录下的所有文件
     * @throws IOException
     * @time 2017年7月30日9:38:05
     */
    public static void getFiles() throws IOException {
        String encoding = System.getProperty("file.encoding");
        fileList.clear();
        fileSizeList.clear();
        fileTimeList.clear();
        String filePath = GetPropertyUtil.getFileAddress("Files");
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            System.out.println(encoding);
            fileSizeList.add(file.length());
            fileTimeList.add(simpleDateFormat.format(new Date(file.lastModified())));
            fileList.add(new String(file.getName().getBytes(encoding),"UTF-8"));
            System.out.println(new String(file.getName().getBytes(encoding),"UTF-8"));
            System.out.println("----------------------------");
        }
    }
}