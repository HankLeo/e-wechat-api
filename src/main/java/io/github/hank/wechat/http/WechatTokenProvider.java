package io.github.hank.wechat.http;

import io.github.hank.wechat.http.model.WechatTokenData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WechatTokenProvider {

    @Autowired
    private RestTemplate restTemplate;

    private static final String WECHAT_TOKEN_API_PATH = "gettoken";

    private static final String CORPID = "ww77ac89dad1f6a39f";

    private static final String CORPSECRET = "PLxtHh93H_vOpYWr5M5mI010fep8zQn3oGUAz8Iak8A";

    private static final Logger logger = LoggerFactory.getLogger(WechatTokenProvider.class);

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    public String getToken() {
        if (cache.get("token") != null) {
            return (String)cache.get("token");
        } else {
            String token = requestToken();
            cache.put("token", token);
            return token;
        }
    }

    private String requestToken() {
        UriComponents uriComponents = new WechatUriComponentBuilder().path(WECHAT_TOKEN_API_PATH)
                .queryParam("corpid", CORPID).queryParam("corpsecret", CORPSECRET).build();
        String uri = uriComponents.toUriString();

        logger.info("Request Wechat token: " + uri);
        WechatTokenData wechatTokenData = restTemplate.getForObject(uri, WechatTokenData.class);
        return wechatTokenData == null ? null : wechatTokenData.getAccess_token();
    }

}
