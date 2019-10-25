package io.github.hank.wechat.http;

public class WechatUriData {

    private static final String WECHAT_API_CONTEXT = "https://qyapi.weixin.qq.com/cgi-bin/";

    private static final String CORPID = "ww77ac89dad1f6a39f";

    private static final String CORPSECRET = "PLxtHh93H_vOpYWr5M5mI010fep8zQn3oGUAz8Iak8A";

    public static final String GET_TOKEN_URL = WECHAT_API_CONTEXT + "gettoken?corpid=" + CORPID + "&corpsecret=" + CORPSECRET;

    public static final String SEND_GROUP_MSG_URL = WECHAT_API_CONTEXT + "appchat/send?access_token=%s";

}
