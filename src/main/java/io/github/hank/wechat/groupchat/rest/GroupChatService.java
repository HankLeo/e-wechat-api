package io.github.hank.wechat.groupchat.rest;

import io.github.hank.wechat.groupchat.model.WechatGroupChangeReq;
import io.github.hank.wechat.groupchat.persistence.GroupChatRepository;
import io.github.hank.wechat.http.WechatTokenProvider;
import io.github.hank.wechat.http.WechatUriComponentBuilder;
import io.github.hank.wechat.groupchat.model.GroupChatInfo;
import io.github.hank.wechat.groupchat.model.WechatGroupChatResponse;
import io.github.hank.wechat.groupchat.model.WechatGroupMsgData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupChatService {

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private WechatTokenProvider wechatTokenProvider;

    @Autowired
    private RestTemplate restTemplate;

    private static final String GROUP_CHAT_PATH = "appchat/";
    private static final String SEND_API = "send";
    private static final String GET_API = "get";
    private static final String CREATE_API = "create";
    private static final String CHANGE_API = "update";

    private static final Logger logger = LoggerFactory.getLogger(GroupChatService.class);

    public void addGroupChat(GroupChatInfo groupChatInfo) {
        String accessToken = wechatTokenProvider.getToken();

        UriComponents uriComponents = new WechatUriComponentBuilder().path(GROUP_CHAT_PATH + CREATE_API)
                .queryParam(WechatUriComponentBuilder.WECHAT_API_TOKEN_PARAM, accessToken).build();
        String uri = uriComponents.toUriString();

        logger.info("Add group: " + uri);
        restTemplate.postForObject(uri, groupChatInfo, String.class);
        // TODO depend on we-chat-api response code
        saveGroupChat(groupChatInfo);
        logger.info("Saved in local DB.");
    }

    public void changeGroupInfo(WechatGroupChangeReq request) {
        String accessToken = wechatTokenProvider.getToken();

        UriComponents uriComponents = new WechatUriComponentBuilder().path(GROUP_CHAT_PATH + CHANGE_API)
                .queryParam(WechatUriComponentBuilder.WECHAT_API_TOKEN_PARAM, accessToken).build();
        String uri = uriComponents.toUriString();

        logger.info("Update group info: " + uri);
        restTemplate.postForObject(uri, request, String.class);

        String chatId = request.getChatid();
        saveGroupChat(getGroupInfoByChatId(chatId));
        logger.info("Updated in local DB.");
    }

    public void sendGroupMsg(WechatGroupMsgData wechatGroupMsgData) {
        String accessToken = wechatTokenProvider.getToken();

        UriComponents uriComponents = new WechatUriComponentBuilder().path(GROUP_CHAT_PATH + SEND_API)
                .queryParam(WechatUriComponentBuilder.WECHAT_API_TOKEN_PARAM, accessToken).build();
        String uri = uriComponents.toUriString();

        logger.info("Send group message: " + uri);
        restTemplate.postForObject(uri, wechatGroupMsgData, String.class);
    }

    public GroupChatInfo getGroupInfoByChatId(String groupId) {
        String accessToken = wechatTokenProvider.getToken();

        UriComponents uriComponents = new WechatUriComponentBuilder().path(GROUP_CHAT_PATH + GET_API)
                .queryParam(WechatUriComponentBuilder.WECHAT_API_TOKEN_PARAM, accessToken)
                .queryParam("chatid", groupId).build();
        String uri = uriComponents.toUriString();

        logger.info("Get group chat info: " + uri);
        return restTemplate.getForObject(uri, WechatGroupChatResponse.class).getChat_info();
    }

    private void saveGroupChat(GroupChatInfo groupChatInfo) {
        groupChatRepository.save(groupChatInfo);
    }

    public List<GroupChatInfo> getGroupInfo() {
        List<GroupChatInfo> list = new ArrayList<>();
        groupChatRepository.findAll().forEach(list::add);
        return list;
    }
}
