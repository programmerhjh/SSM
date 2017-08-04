package tool;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by acer on 2017/7/30.
 */
public class TurnZip {


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
        ZipOutputStream outputStream = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(zipName)));
        outputStream.putNextEntry(new ZipEntry(storeName));
        outputStream.setEncoding("GBK");

        FileCopyUtils.copy(mFile.getInputStream(), outputStream);
    }


}
