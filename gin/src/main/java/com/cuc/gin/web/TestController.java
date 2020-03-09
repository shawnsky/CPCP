package com.cuc.gin.web;

import com.cuc.gin.annotation.AdminRequired;
import com.cuc.gin.mapper.TestEntryMapper;
import com.cuc.gin.mapper.TestResultMapper;
import com.cuc.gin.model.TestEntryEntity;
import com.cuc.gin.model.TestResultEntity;
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
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private TestResultMapper resultMapper;

    private static Map<String, Integer> scoreSheet = new HashMap<>();
    static {
        scoreSheet.put("A", 0);
        scoreSheet.put("B", 4);
        scoreSheet.put("C", 6);
        scoreSheet.put("D", 10);
    }

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

    @RequestMapping(value = "/test/result", method = RequestMethod.POST)
    public HTTPMessage<Void> submitResult(@RequestBody Map map) {
        // TODO: 2020/3/9 query user id from request attribute and compare with id via request param
        String userId = (String) map.get("userId");
        List<String> seq = (List<String>) map.get("checkedList");
        Map<Integer, String> choice = new HashMap<>();
        seq.forEach(checked -> {
            int qIndex = Integer.parseInt(String.valueOf(checked.charAt(0)));
            String answer = String.valueOf(checked.charAt(1));
            choice.put(qIndex, answer);
        });
        StringBuilder sb = new StringBuilder();
        int value = 0;
        for (int i=0;i<10;i++) {
            sb.append(choice.get(i));
            value += scoreSheet.get(choice.get(i));
        }
        TestResultEntity resultEntity = new TestResultEntity();
        resultEntity.setResult(sb.toString());
        resultEntity.setUserId(Long.parseLong(userId));
        resultEntity.setValue(value);
        LocalDateTime ldt = LocalDateTime.now();
        Instant ins = ldt.atZone(ZoneId.of("Asia/Shanghai")).toInstant();
        resultEntity.setCreateTime(ins.toEpochMilli());
        resultMapper.add(resultEntity);
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK
        );
    }

    @RequestMapping(value = "/test/result", method = RequestMethod.GET)
    @AdminRequired
    public HTTPMessage<List<TestResultEntity>> getAllResults(HttpServletRequest request, HttpServletResponse response) {
        return new HTTPMessage<>(
                HTTPMessageCode.Common.OK,
                HTTPMessageText.Common.OK,
                resultMapper.getAll()
        );
    }
}
