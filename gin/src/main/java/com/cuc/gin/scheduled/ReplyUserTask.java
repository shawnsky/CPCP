package com.cuc.gin.scheduled;

import com.cuc.gin.mapper.ChatMsgMapper;
import com.cuc.gin.mapper.TalkMapper;
import com.cuc.gin.model.ChatMsgEntity;
import com.cuc.gin.model.TalkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author : Chen X.T.
 * @since : 2020/3/10, 周二
 **/
@Component
public class ReplyUserTask {

    @Autowired
    private TalkMapper talkMapper;

    @Autowired
    private ChatMsgMapper chatMsgMapper;


//    @Scheduled(fixedRate = 10000)  // each 10s
    @Scheduled(cron = "0 0 8 * * ?")  // everyday 8:00a.m.
    public void reply() {
        List<TalkEntity> talkEntities = talkMapper.getAll();
        talkEntities.forEach(e -> {
            if (e.getReplied() == 0) {
                // reply to user via chat
                LocalDateTime ldt = LocalDateTime.now();
                Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
                String content = "我们已经看到您的留言，请等待管理员与您联系";
                ChatMsgEntity msgEntity = new ChatMsgEntity(0L, e.getUserId(), content, ins.toEpochMilli());
                chatMsgMapper.insert(msgEntity);
                // mark as replied
                e.setReplied(1);
                talkMapper.update(e);
            }
        });
        System.out.println("自动回复任务已完成");
    }
}
