package com.sunll.common.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunll
 * on 2017/10/30
 */
@Service
public class FastDFSClient {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    private StorageClient1 storageClient = null;

    public FastDFSClient() {
    }

    public FastDFSClient(String conf) throws Exception {

        if (conf.contains("classpath:")) {
            String url = this.getClass().getResource("/").getPath();
            url = url.substring(1);
            conf = conf.replace("classpath:", url);
        }
        ClientGlobal.initByProperties(conf);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        storageClient = new StorageClient1(trackerServer, null);
    }


    public String uploadFile(String fileName) throws Exception {
        return uploadFile(null, fileName, null, null);
    }

    public String uploadFile(String fileName, String extName) throws Exception {
        return uploadFile(null, fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    private String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        return storageClient.upload_file1(fileContent, extName, metas);
    }

    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    public String uploadFile(byte[] fileContent, String extName) throws Exception {
        return uploadFile(fileContent, extName, null);
    }


    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名，不包含（.）
     * @param metas    文件扩展信息
     * @return
     * @throws Exception
     */
    private String uploadFile(String gropuName, String fileName, String extName, NameValuePair[] metas) throws Exception {
        return storageClient.upload_file1(gropuName, fileName, extName, metas);
    }

    public String uploadGropuFile(String gropuName, String fileName) throws Exception {
        return uploadFile(gropuName, fileName, null, null);
    }

    public String uploadFile(String gropuName, String fileName, String extName) throws Exception {
        return uploadFile(gropuName, fileName, extName, null);
    }

    /**
     * 上传文件方法
     * <p>Title: uploadFile</p>
     * <p>Description: </p>
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    private String uploadFile(String gropuName, byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {

        return storageClient.upload_file1(gropuName, fileContent, extName, metas);
    }

    public String uploadFile(String gropuName, byte[] fileContent) throws Exception {
        return uploadFile(gropuName, fileContent, null, null);
    }

    public String uploadFile(String gropuName, byte[] fileContent, String extName) throws Exception {
        return uploadFile(gropuName, fileContent, extName, null);
    }

    private byte[] downloadFile(String fileUrl) throws Exception {
        return storageClient.download_file1(fileUrl);
    }


    /**
     * 文件下载
     *
     * @param fileUrl  服务器上的文件路径
     * @param response
     * @throws Exception
     */
    public void downloadFile(String fileUrl, HttpServletResponse response) throws Exception {
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileUrl + "\"");
        byte[] bytes = downloadFile(fileUrl);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        byte[] b = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件存储路径
     * @return
     * @throws Exception
     */
    public int deleteFile(String fileUrl) throws Exception {
        return storageClient.delete_file1(fileUrl);
    }


   /* public String upFile() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("fdfs_client.properties");
        return FDFS_SERVER_URL+fastDFSClient.uploadFile("C:\\Users\\Administrator\\Desktop\\1.jpg","jpg");
    }

    public void downFile(String fileUrl, HttpServletResponse response) throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("fdfs_client.properties");
        fastDFSClient.downloadFile(fileUrl,response);
    }*/

}
