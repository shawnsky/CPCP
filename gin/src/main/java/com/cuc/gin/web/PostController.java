package com.cuc.gin.web;

import com.cuc.gin.mapper.PostMapper;
import com.cuc.gin.model.PostEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
public class PostController {

    @Autowired
    private PostMapper postMapper;

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public HTTPMessage<List<PostEntity>> getPosts() {
        List<PostEntity> list = postMapper.getAll();
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                list
        );

    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public HTTPMessage<Void> addPost(@RequestBody Map map, HttpServletResponse response) {
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        String cover = (String) map.get("cover");
        // TODO: get admin user id
        if (Strings.isNullOrEmpty(title)) {
            throw new IllegalArgumentException();
        }
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        PostEntity entity = new PostEntity();
        entity.setTitle(title);
        entity.setContent(content);
        entity.setCover(cover);
        entity.setCreateTime(ins.toEpochMilli());
        postMapper.insertOne(entity);
        response.setStatus(HttpStatus.CREATED.value());
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public HTTPMessage<PostEntity> getPost(@PathVariable Long id, HttpServletResponse response) {
        PostEntity postEntity = postMapper.getOne(id);
        if (postEntity == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new HTTPMessage<>(
                    HTTPMessageCode.Common.FAILURE,
                    HTTPMessageText.Common.FAILURE
            );
        }
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                postEntity
        );
    }


    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public HTTPMessage<Void> removePost(@PathVariable Long id, HttpServletResponse response) {
        postMapper.deleteOne(id);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }
}
