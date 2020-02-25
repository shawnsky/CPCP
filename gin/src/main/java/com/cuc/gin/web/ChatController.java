package com.cuc.gin.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author : Chen X.T.
 * @since : 2020/2/2, 周日
 **/
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public HTTPMessage<Void> sendMessage(@RequestParam("from_id") String from,
                                         @RequestParam("to_id") String to,
                                         @RequestParam("content") String content) {
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
        UserEntity a = userMapper.getOne(Long.parseLong(aId));
        UserEntity b = userMapper.getOne(Long.parseLong(bId));
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                new ChatMsgVo(aId, a.getUsername(), a.getAvatar(), bId, b.getUsername(), b.getAvatar(), list)
        );


    }
}
