package com.cuc.gin.web;

import com.cuc.gin.annotation.AdminRequired;
import com.cuc.gin.mapper.TalkMapper;
import com.cuc.gin.model.TalkEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * @author : Chen X.T.
 * @since : 2020/3/9, 周一
 **/
@RestController
public class TalkController {

    @Autowired
    private TalkMapper talkMapper;

    @RequestMapping(value = "/talk", method = RequestMethod.GET)
    @AdminRequired
    public HTTPMessage<List<TalkEntity>> getAll(HttpServletRequest request, HttpServletResponse response) {
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                talkMapper.getAll()
        );
    }

    @RequestMapping(value = "/talk", method = RequestMethod.POST)
    public HTTPMessage<Void> submit(HttpServletRequest request, @RequestBody Map map) {
        // TODO: 2020/3/9 query user id from request attribute and compare with id via request param
        // String userId = (String) request.getAttribute("userId");
        String userId = (String) map.get("userId");
        String username = (String) map.get("username");
        String content = (String) map.get("content");
        if (Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(content)) {
            return new HTTPMessage<>(
                    HTTPMessageCode.Common.FAILURE,
                    HTTPMessageText.Common.FAILURE
            );
        }
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        TalkEntity talkEntity = new TalkEntity();
        talkEntity.setUserId(Long.parseLong(userId));
        talkEntity.setUsername(username);
        talkEntity.setContent(content);
        talkEntity.setCreateTime(ins.toEpochMilli());
        talkMapper.add(talkEntity);
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }

    @RequestMapping(value = "/talk/{id}", method = RequestMethod.DELETE)
    @AdminRequired
    public HTTPMessage<Void> delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        talkMapper.remove(id);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }
}
