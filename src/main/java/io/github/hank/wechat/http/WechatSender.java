package io.github.hank.wechat.http;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WechatSender {

    private CloseableHttpClient httpClient;

    private HttpGet httpGet;

    private HttpPost httpPost;

    private static final Logger logger = LoggerFactory.getLogger(WechatSender.class);

    private static final Gson gson = new Gson();

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    public String getToken() throws IOException {
        if (cache.get("token") != null) {
            return (String)cache.get("token");
        } else {
            String token = requestToken();
            cache.put("token", token);
            return token;
        }
    }

    private String requestToken() throws IOException {
        httpClient = HttpClients.createDefault();
        String res;
        try {
            httpGet = new HttpGet(WechatUriData.GET_TOKEN_URL);
            logger.info("Request Wechat token: " + WechatUriData.GET_TOKEN_URL);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                res = EntityUtils.toString(httpEntity, "utf-8");
                return gson.fromJson(res, WechatToken.class).getAccess_token();
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }

}
