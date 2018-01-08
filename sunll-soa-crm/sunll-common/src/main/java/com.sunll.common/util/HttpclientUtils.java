package com.sunll.common.util;

import com.sunll.common.api.ApiAcceptData;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30.
 */
public class HttpclientUtils {
    public static String doPostByJson(String url, Map<String, Object> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = null;
        try {
            HttpPost post = new HttpPost(url);
            JSONObject jsonParam = new JSONObject();
            if (param != null) {
                for (String key : param.keySet()) {
                    jsonParam.put(key,param.get(key));
                }
            }
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPostOnApiAcceptData(String url, ApiAcceptData param) {

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = null;
        try {
            HttpPost post = new HttpPost(url);
            JSONObject jsonParam = new JSONObject();
            JSONObject object = JSONObject.fromObject(param);
            StringEntity entity = new StringEntity(object.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
