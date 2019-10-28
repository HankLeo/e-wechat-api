package io.github.hank.wechat.messaging.rest;

import io.github.hank.wechat.messaging.model.WechatGroupMsgData;
import io.github.hank.wechat.messaging.model.WechatMsgText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendMessage")
public class WechatMsgController {

    @Autowired
    private WechatMsgService wechatMsgService;

    @PostMapping("/{groupId}")
    public void sendGroupMsg(@PathVariable String groupId,
                             @RequestBody String text) {
        WechatGroupMsgData wechatGroupMsgData = new WechatGroupMsgData(groupId, "text",
                new WechatMsgText(text), 0);
        wechatMsgService.sendGroupMsg(wechatGroupMsgData);
    }

    @RequestMapping
    public String hello() {
        return "Hi! This is Hank!";
    }

}
