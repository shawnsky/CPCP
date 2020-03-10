package com.cuc.gin.web;

import com.cuc.gin.annotation.AdminRequired;
import com.cuc.gin.mapper.ChatMsgMapper;
import com.cuc.gin.mapper.UserMapper;
import com.cuc.gin.model.ChatMsgEntity;
import com.cuc.gin.model.UserEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;
import com.cuc.gin.vo.ChatMsgVo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author : Chen X.T.
 * @since : 2020/2/2, 周日
 **/
@RestController
public class ChatController {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public HTTPMessage<Void> sendMessage(@RequestBody Map map) {
        String from = (String) map.get("from_id");
        String to = (String) map.get("to_id");
        String content = (String) map.get("content");
        // FIXME: 2020/3/10 fix admin id = 0
        if (Strings.isNullOrEmpty(from) || Strings.isNullOrEmpty(to) || Strings.isNullOrEmpty(content)) {
            throw new IllegalArgumentException();
        }
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        ChatMsgEntity msgEntity = new ChatMsgEntity(Long.parseLong(from), Long.parseLong(to), content, ins.toEpochMilli());
        System.out.println(Date.from(ins));
        chatMsgMapper.insert(msgEntity);
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public HTTPMessage<ChatMsgVo> queryMessage(@RequestParam("a_id") String aId,
                                               @RequestParam("b_id") String bId) {
        // FIXME: 2020/3/10 fix a is admin, a_id = 0
        if (Strings.isNullOrEmpty(aId) || Strings.isNullOrEmpty(bId)) {
            throw new IllegalArgumentException();
        }
        // Query A to B
        List<ChatMsgEntity> a2bList = chatMsgMapper.queryByFromTo(Long.parseLong(aId), Long.parseLong(bId));
        // Query B to A
        List<ChatMsgEntity> list = chatMsgMapper.queryByFromTo(Long.parseLong(bId), Long.parseLong(aId));
        // Merge and Sort by timestamp
        list.addAll(a2bList);
        list.sort((o1, o2) -> (int) (o1.getCreateTime()-o2.getCreateTime()));
        // Query users' profile
        UserEntity b = userMapper.getOne(Long.parseLong(bId));
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                new ChatMsgVo("0", "admin", "https://i.loli.net/2020/03/02/TP81J27RbG3yixC.jpg", bId, b.getUsername(), b.getAvatar(), list)
        );


    }
    @RequestMapping(value = "/message/user", method = RequestMethod.GET)
    @AdminRequired
    public HTTPMessage<List<UserEntity>> getChatUsers(HttpServletRequest request, HttpServletResponse response) {
        List<ChatMsgEntity> list = chatMsgMapper.queryAll();
        List<UserEntity> userEntitySet = new ArrayList<>();
        Set<Long> userIds = new HashSet<>();
        list.forEach(chat -> {
            if (chat.getFromId() != 0 && !userIds.contains(chat.getFromId())) {
                userEntitySet.add(userMapper.getOne(chat.getFromId()));
                userIds.add(chat.getFromId());
            } else if (chat.getToId() != 0 && !userIds.contains(chat.getToId())) {
                userEntitySet.add(userMapper.getOne(chat.getToId()));
                userIds.add(chat.getToId());
            }
        });
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                userEntitySet
        );
    }
}
