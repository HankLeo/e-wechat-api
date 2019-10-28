package io.github.hank.wechat.messaging.rest;

import io.github.hank.wechat.http.WechatTokenProvider;
import io.github.hank.wechat.http.WechatUriComponentBuilder;
import io.github.hank.wechat.messaging.model.WechatGroupMsgData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

@Service
public class WechatMsgService {

    @Autowired
    private WechatTokenProvider wechatTokenProvider;

    @Autowired
    private RestTemplate restTemplate;

    private static final String MSG_PATH = "appchat";
    private static final String SEND_API = "send";

    private static final Logger logger = LoggerFactory.getLogger(WechatMsgService.class);

    public void sendGroupMsg(WechatGroupMsgData wechatGroupMsgData) {
        String accessToken = wechatTokenProvider.getToken();

        UriComponents uriComponents = new WechatUriComponentBuilder().path(MSG_PATH + "/" + SEND_API)
                .queryParam(WechatUriComponentBuilder.WECHAT_API_TOKEN_PARAM, accessToken).build();
        String uri = uriComponents.toUriString();

        logger.info("Send group message: " + uri);
        restTemplate.postForObject(uri, wechatGroupMsgData, String.class);
    }

}
