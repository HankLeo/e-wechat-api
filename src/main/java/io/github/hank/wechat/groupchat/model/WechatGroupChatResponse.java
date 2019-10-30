package io.github.hank.wechat.groupchat.model;

import io.github.hank.wechat.util.WechatResponse;

public class WechatGroupChatResponse extends WechatResponse {

    private GroupChatInfo chat_info;

    public GroupChatInfo getChat_info() {
        return chat_info;
    }

    public void setChat_info(GroupChatInfo chat_info) {
        this.chat_info = chat_info;
    }

}
