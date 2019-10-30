package io.github.hank.wechat.groupchat.rest;

import io.github.hank.wechat.groupchat.model.GroupChatInfo;
import io.github.hank.wechat.groupchat.model.WechatGroupChangeReq;
import io.github.hank.wechat.groupchat.model.WechatGroupMsgData;
import io.github.hank.wechat.groupchat.model.WechatMsgText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/groupChat")
public class GroupChatController {

    @Autowired
    private GroupChatService groupChatService;

    /**
     * Create a new WeChat group
     * @param name
     * @param owner
     * @param chatid
     * @param userlist
     */
    @PostMapping("/new")
    public void createGroup(@RequestParam("name") @NotNull String name,
                            @RequestParam("owner") @NotNull String owner,
                            @RequestParam("chatid") @NotNull String chatid,
                            @RequestBody @NotNull List<String> userlist) {
        groupChatService.addGroupChat(new GroupChatInfo(chatid, name, owner, userlist));
    }

    @PostMapping("/update")
    public void updategroup(@RequestParam("chatid") @NotNull String chatid,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "owner", required = false) String owner,
                            @RequestParam(value = "add_user_list", required = false) List<String> addUserList,
                            @RequestParam(value = "del_user_list", required = false) List<String> delUserList) {
        WechatGroupChangeReq request = new WechatGroupChangeReq(chatid, name, owner, addUserList, delUserList);
        groupChatService.changeGroupInfo(request);
    }

    /**
     * Send message to a WeChat group
     * @param groupId
     * @param text
     */
    @PostMapping("/sendMessage/{groupId}")
    public void sendGroupMsg(@PathVariable String groupId,
                             @RequestBody String text) {
        WechatGroupMsgData wechatGroupMsgData = new WechatGroupMsgData(groupId, "text",
                new WechatMsgText(text), 0);
        groupChatService.sendGroupMsg(wechatGroupMsgData);
    }

    /**
     * Get WeChat group information
     * @param groupId
     * @return
     */
    @GetMapping("/groupInfo/{groupId}")
    public GroupChatInfo getGroupInfoByChatID(@PathVariable String groupId) {
        return groupChatService.getGroupInfoByChatId(groupId);
    }

    /**
     * Get all WeChat groups
     * @return
     */
    @GetMapping("/groupInfo")
    public List<GroupChatInfo> getGroupInfo() {
        return groupChatService.getGroupInfo();
    }

    @RequestMapping
    public String hello() {
        return "Hi! This is Hank!";
    }

}
