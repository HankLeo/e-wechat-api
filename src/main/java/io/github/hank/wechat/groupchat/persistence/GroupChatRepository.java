package io.github.hank.wechat.groupchat.persistence;

import io.github.hank.wechat.groupchat.model.GroupChatInfo;
import org.springframework.data.repository.CrudRepository;

public interface GroupChatRepository extends CrudRepository<GroupChatInfo, String> {

}
