package com.cuc.gin.web;

import com.cuc.gin.annotation.AdminRequired;
import com.cuc.gin.mapper.BoardMapper;
import com.cuc.gin.model.BoardEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * @author : Chen X.T.
 * @since : 2020/2/26, 周三
 **/
@RestController
public class BoardController {

    @Autowired
    private BoardMapper boardMapper;

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public HTTPMessage<BoardEntity> getBoard() {
        List<BoardEntity> list = boardMapper.getAll();
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                list.get(0)
        );
    }

    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    @AdminRequired
    public HTTPMessage<Void> updateBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map) {
        String newContent = (String) map.get("content");
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        BoardEntity boardEntity = new BoardEntity(newContent, ins.toEpochMilli());
        boardEntity.setId(boardMapper.getAll().get(0).getId());
        boardMapper.updateOne(boardEntity);
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );

    }
}
