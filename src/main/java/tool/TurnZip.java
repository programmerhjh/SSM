package tool;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 把文件转化为zip压缩包类型工具类
 * Created by acer on 2017/7/30.
 */
public class TurnZip {

    /**
     * 生成压缩包文件名
     * @param name
     * @return
     * @time 2017年7月30日9:44:23
     */
    static String zipName(String name) {
        String prefix = "";
        if (name.indexOf(".") != -1) {
            prefix = name.substring(0, name.lastIndexOf("."));
        } else {
            prefix = name;
        }
        return prefix + ".zip";
    }

    /** 压缩一个文件 */
    public static void compressFile(MultipartFile mFile) throws IOException {
        String storeName = mFile.getOriginalFilename();
        String noZipName = GetPropertyUtil.getFileAddress("Files")+ storeName;
        String zipName = zipName(noZipName);
        ZipEntry zipEntry = new ZipEntry(storeName);
        zipEntry.setUnixMode(644);
        ZipOutputStream outputStream = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(zipName)));
        outputStream.putNextEntry(zipEntry);
        outputStream.setEncoding("utf-8");

        FileCopyUtils.copy(mFile.getInputStream(), outputStream);
    }


}
