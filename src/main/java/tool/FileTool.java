package tool;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileTool {
    public static ArrayList<String> fileList = new ArrayList<String>();
    public static ArrayList<Long> fileSizeList = new ArrayList<Long>();
    public static ArrayList<String> fileTimeList = new ArrayList<String>();
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /*
     * 得到某一路径下所有的目录文件
     */


    public static void getFiles(){
        fileList.clear();
        fileSizeList.clear();
        fileTimeList.clear();
        String filePath = "F://upload//uploadFiles";
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            fileSizeList.add(file.length());
            fileTimeList.add(simpleDateFormat.format(new Date(file.lastModified())));
            fileList.add(file.getName());
        }
    }
}