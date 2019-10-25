package io.github.hank.wechat.messaging.rest;

import io.github.hank.wechat.messaging.model.WechatGroupMsgData;
import io.github.hank.wechat.messaging.model.WechatMsgText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WechatMsgController {

    @Autowired
    private WechatMsgService wechatMsgService;

    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage/{groupId}")
    public void sendGroupMsg(@PathVariable String groupId,
                             @RequestBody String text) {
        WechatGroupMsgData wechatGroupMsgData = new WechatGroupMsgData(groupId, "text",
                new WechatMsgText(text), 0);
        wechatMsgService.sendGroupMsg(wechatGroupMsgData);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sendMessage")
    public String hello() {
        return "Hi!";
    }

}
