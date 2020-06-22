package batch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class NxSmsService {
    public static CloseableHttpClient httpClient;

    static {
        httpClient = HttpClientBuilder.create()
                .setMaxConnPerRoute(2)
                .setMaxConnTotal(20)
                .build();
    }

    static boolean notEmpty(String i) {
        return null == i || i.length() == 0;
    }

    public static String send(String phone, String content, String srcAddr, String appkey, String secretkey, String sysMessageId) {
        List<NameValuePair> form = new ArrayList<NameValuePair>();
        form.add(new BasicNameValuePair("appkey", appkey));
        form.add(new BasicNameValuePair("secretkey", secretkey));
        form.add(new BasicNameValuePair("phone", phone));
        form.add(new BasicNameValuePair("content", content));

        if(notEmpty(srcAddr))
            form.add(new BasicNameValuePair("source_address", srcAddr));

        if(notEmpty(sysMessageId))
            form.add(new BasicNameValuePair("sys_messageid", sysMessageId));

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
            HttpPost httpPost = new HttpPost("http://api.nxcloud.com/api/sms/mtsend");
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
            httpResponse.close();

            JSONObject obj = JSON.parseObject(result);
            String code = obj.getString("code");
            if (code.equals("0")) {
                return obj.getString("messageid");
            } else {

                System.out.println("请求失败，报错原因：" + obj.getString("result"));
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        String messageId = send("11111", "2222222", "", "11111", "33333", "");
        // messageId 为空，代表发送失败
    }

}
