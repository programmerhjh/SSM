package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传下载工具
 * Created by acer on 2017/7/30.
 */
public class FileDownUpUtil {

    /**
     * 下载文件
     * @param request
     * @param response
     * @param storeName
     * @param realName
     * @param ctxPath
     * @throws Exception
     * @time 2017年7月30日9:36:25
     */
    public static void downloadFile(HttpServletRequest request,
                                HttpServletResponse response, String storeName,
                                String realName,String ctxPath) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis;
        BufferedOutputStream bos;


        String downLoadPath = ctxPath + storeName;

        long fileLength = new File(downLoadPath).length();

        response.setHeader("Content-disposition", "attachment; filename="
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }


    /**
     * 删除服务器中的文件
     * @param fileName
     * @return
     * @throws IOException
     * @time 2017年7月30日9:36:54
     */
    public static boolean deleteFile(String fileName) throws IOException {
        boolean flag = true;
        File file = new File(GetPropertyUtil.getFileAddress("Files")+fileName);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }else {
            flag = false;
        }
        return flag;
    }

}
