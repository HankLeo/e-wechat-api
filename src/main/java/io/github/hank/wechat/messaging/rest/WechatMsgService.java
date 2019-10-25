package io.github.hank.wechat.messaging.rest;

import com.google.gson.Gson;
import io.github.hank.wechat.http.WechatSender;
import io.github.hank.wechat.http.WechatUriData;
import io.github.hank.wechat.messaging.model.WechatGroupMsgData;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatMsgService {

    @Autowired
    private WechatSender wechatSender;

    private static final Logger logger = LoggerFactory.getLogger(WechatMsgService.class);

    private static final Gson gson = new Gson();

    private CloseableHttpClient httpClient;

    private HttpPost httpPost;

    public void sendGroupMsg(WechatGroupMsgData wechatGroupMsgData) {
        httpClient = HttpClients.createDefault();
        try {
            String token = wechatSender.getToken();
            httpPost = new HttpPost(String.format(WechatUriData.SEND_GROUP_MSG_URL, token));
            httpPost.setEntity(new StringEntity(gson.toJson(wechatGroupMsgData), "utf-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String res;
            try {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity, "utf-8");
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
