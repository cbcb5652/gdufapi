package com.gdufcb.gdufapi.util;

import com.gdufcb.gdufapi.Pojo.gdufUser;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @Author 晨边#CB
 * @Date:created in  2019/10/19 10:14
 * @Version V1.0
 **/

public class UserMsg {

    public static String interfaceUtilPost(String path,gdufUser gdufUser) throws IOException {
        String token = gdufUser.getToken();
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(path);
        postMethod.addParameter("xh","171543113");
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        postMethod.addRequestHeader("token",token);
        httpClient.executeMethod(postMethod);

        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }

    public static String interfaceUtilGet(String path,String token) throws IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(path);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
        getMethod.addRequestHeader("Content-Type", "application/json");
        if(token!=null){
            getMethod.addRequestHeader("token",token);
        }
        httpClient.executeMethod(getMethod);

        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) throws IOException {

        String result = interfaceUtilGet("http://jwxt.gduf.edu.cn/app.do?method=authUser&xh=171543113&pwd=ch1501695397",null);//get请求
        System.out.println(result);
    }

}
