package io.github.hank.wechat.groupchat.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class GroupChatInfo {

    @Id
    private String chatid;

    private String name;

    private String owner;

    @ElementCollection
    private List<String> userlist;

    public GroupChatInfo() {
    }

    public GroupChatInfo(String chatid, String name, String owner, List<String> userlist) {
        this.chatid = chatid;
        this.name = name;
        this.owner = owner;
        this.userlist = userlist;
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

    public List<String> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<String> userlist) {
        this.userlist = userlist;
    }

}
