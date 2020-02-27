package com.cuc.gin.web;

import com.cuc.gin.mapper.TestEntryMapper;
import com.cuc.gin.model.TestEntryEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Chen X.T.
 * @since : 2020/2/27, 周四
 **/
@RestController
public class TestController {

    @Autowired
    private TestEntryMapper entryMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public HTTPMessage<List<TestEntryEntity>> getEntries() {
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                entryMapper.getAll()
        );
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public HTTPMessage<Void> updateEntries(@RequestBody Map map) {
        List<String> questions = (List<String>) map.get("questions");
        List<String> choices = (List<String>) map.get("choices");
        List<TestEntryEntity> listToSave = new ArrayList<>();
        try {
            for (int i=0;i<10;i++) {
                TestEntryEntity entity = new TestEntryEntity();
                entity.setQuestion(questions.get(i));
                String rawInput = choices.get(i);
                String[] abcd = rawInput.split(" ");
                entity.setAnsA(abcd[0]);
                entity.setAnsB(abcd[1]);
                entity.setAnsC(abcd[2]);
                entity.setAnsD(abcd[3]);
                listToSave.add(entity);
            }
        } catch (Exception e) {
            return new HTTPMessage<>(
                    HTTPMessageCode.Common.FAILURE,
                    HTTPMessageText.Common.FAILURE
            );
        }
        // Data parse ok, ready to overwrite safely
        entryMapper.removeAll();
        listToSave.forEach(entity -> entryMapper.add(entity));
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }
}
