package io.github.hank.wechat.groupchat.model;

import java.util.Objects;

public class WechatGroupMsgData {

    private String chatid;

    private String msgtype;

    private WechatMsgText text;

    private int safe;

    public WechatGroupMsgData(String chatid, String msgtype, WechatMsgText text, int safe) {
        this.chatid = chatid;
        this.msgtype = msgtype;
        this.text = text;
        this.safe = safe;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public WechatMsgText getText() {
        return text;
    }

    public void setText(WechatMsgText text) {
        this.text = text;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WechatGroupMsgData that = (WechatGroupMsgData) o;
        return safe == that.safe &&
                Objects.equals(chatid, that.chatid) &&
                Objects.equals(msgtype, that.msgtype) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatid, msgtype, text, safe);
    }

}
