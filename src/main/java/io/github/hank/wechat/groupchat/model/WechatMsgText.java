package io.github.hank.wechat.groupchat.model;

import java.util.Objects;

public class WechatMsgText {

    String content;

    public WechatMsgText(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WechatMsgText that = (WechatMsgText) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
