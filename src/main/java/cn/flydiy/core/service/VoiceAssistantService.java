package cn.flydiy.core.service;

import cn.flydiy.core.web.ResponseData;
import org.ansj.domain.Term;

import java.util.List;
import java.util.Map;

/**
 * Created by player on 2018/1/9.
 */
public interface VoiceAssistantService {
    ResponseData analyzeSentence(String sentence);

    List<Term> test(String sentence);
}
