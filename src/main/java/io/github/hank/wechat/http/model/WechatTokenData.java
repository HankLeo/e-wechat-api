package io.github.hank.wechat.http.model;

import java.util.Objects;

public class WechatTokenData {

    private int errcode;

    private  String errmsg;

    private String access_token;

    private int expires_in;

    public WechatTokenData(int errcode, String errmsg, String access_token, int expires_in) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WechatTokenData that = (WechatTokenData) o;
        return errcode == that.errcode &&
                expires_in == that.expires_in &&
                Objects.equals(errmsg, that.errmsg) &&
                Objects.equals(access_token, that.access_token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errcode, errmsg, access_token, expires_in);
    }

}
