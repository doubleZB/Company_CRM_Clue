package com.sunll.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunll
 * on 2017/10/30
 */
public class DownLoadFileUtil {

    public static void downLoadFile(String fileUrl,InputStream inputStream, HttpServletResponse response){
        // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileUrl + "\"");
            // 循环取出流中的数据
            byte[] buf = new byte[1024];
            int len;
            try {
                while ((len = inputStream.read(buf)) > 0)
                    response.getOutputStream().write(buf, 0, len);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
