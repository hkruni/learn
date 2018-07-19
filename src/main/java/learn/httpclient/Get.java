package learn.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Get {

    public static String HttpGet(String url) {

        Logger logger = Logger.getLogger(Get.class);
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpCilent = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        RequestConfig requestConfig = RequestConfig.
                custom().setConnectTimeout(5000) // 设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000).setRedirectsEnabled(true)// 默认允许自动重定向
                .build();
        HttpGet httpGet2 = new HttpGet(url);
        httpGet2.setConfig(requestConfig);
        httpGet2.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        httpGet2.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        String srtResult =null;
        int StatusCode=404;
        try {
            HttpResponse httpResponse = httpCilent.execute(httpGet2);
            StatusCode=httpResponse.getStatusLine().getStatusCode();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                srtResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
                logger.info("get/"+StatusCode+":"+srtResult);
                return srtResult;
            } else {
                srtResult = EntityUtils.toString(httpResponse.getEntity());// 获得返回的结果
                logger.info("get/"+StatusCode+":"+srtResult);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }


    public static void testPost() throws Exception {

        //创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8080/register");
        //创建一个Entity，模拟表单数据
        List<NameValuePair> formList = new ArrayList<>();
        //添加表单数据
        formList.add(new BasicNameValuePair("name", "小明"));
        formList.add(new BasicNameValuePair("password", "10086"));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        //设置请求的内容
        post.setEntity(entity);
        //设置请求的报文头部的编码
        post.setHeader(
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        //Accept必须设置json，否则406
        post.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));
        //执行post请求
        CloseableHttpResponse response = client.execute(post);
        //获取响应码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            //获取数据
            String resStr = EntityUtils.toString(response.getEntity());
            //输出
            System.out.println("请求成功,请求返回内容为: " + resStr);
        } else {
            //输出
            System.out.println("请求失败,错误码为: " + statusCode);
        }
        //关闭response和client
        response.close();
        client.close();

    }


    public static void main(String[] args) throws Exception{

        String url = "https://www.baidu.com";
        //System.out.println(HttpGet(url));
        testPost();
    }
}
