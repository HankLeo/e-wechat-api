package io.github.hank.wechat.http;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WechatUriComponentBuilder extends UriComponentsBuilder {

    public static final String WECHAT_API_SCHEMA = "https";

    public static final String WECHAT_API_HOST = "qyapi.weixin.qq.com/cgi-bin";

    public static final String WECHAT_API_TOKEN_PARAM = "access_token";

    public WechatUriComponentBuilder() {
        super();
        this.scheme(WECHAT_API_SCHEMA);
        this.host(WECHAT_API_HOST);
    }

}
