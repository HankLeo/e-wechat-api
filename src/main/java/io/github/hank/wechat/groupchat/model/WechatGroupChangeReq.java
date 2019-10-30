package io.github.hank.wechat.groupchat.model;

import java.util.List;

public class WechatGroupChangeReq {

    private String chatid;

    private String name;

    private String owner;

    private List<String> add_user_list;

    private List<String> del_user_list;

    public WechatGroupChangeReq(String chatid, String name, String owner, List<String> add_user_list, List<String> del_user_list) {
        this.chatid = chatid;
        this.name = name;
        this.owner = owner;
        this.add_user_list = add_user_list;
        this.del_user_list = del_user_list;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getAdd_user_list() {
        return add_user_list;
    }

    public void setAdd_user_list(List<String> add_user_list) {
        this.add_user_list = add_user_list;
    }

    public List<String> getDel_user_list() {
        return del_user_list;
    }

    public void setDel_user_list(List<String> del_user_list) {
        this.del_user_list = del_user_list;
    }

}
