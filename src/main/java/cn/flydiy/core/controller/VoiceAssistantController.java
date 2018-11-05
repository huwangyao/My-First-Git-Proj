package cn.flydiy.core.controller;

import cn.flydiy.common.util.CollectionUtils;
import cn.flydiy.common.util.FileUtils;
import cn.flydiy.common.util.StringUtil;
import cn.flydiy.core.annotation.WebController;
import cn.flydiy.core.common.SpringContext;
import cn.flydiy.core.service.VoiceAssistantService;
import cn.flydiy.core.web.ResponseData;
import cn.flydiy.metadata.util.VoiceRecognition;
import cn.flydiy.web.service.AttachmentService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by player on 2017/12/29.
 */
@WebController
public class VoiceAssistantController extends BaseController{

    //        n/名词 np/人名 ns/地名 ni/机构名 nz/其它专名
    //        m/数词 q/量词 mq/数量词 t/时间词 f/方位词 s/处所词
    //        v/动词 a/形容词 d/副词 h/前接成分 k/后接成分 i/习语
    //        j/简称 r/代词 c/连词 p/介词 u/助词 y/语气助词
    //        e/叹词 o/拟声词 g/语素 w/标点 x/其它

    @Autowired
    private VoiceAssistantService voiceAssistantService;

    @Autowired
    private AttachmentService attachmentService;

    public void analyzeVoice2(){
        String sentence = MapUtils.getString(getParamMap(),"sentence");
        ResponseData responseData = null;
        responseData = voiceAssistantService.analyzeSentence(sentence);
        if(responseData == null){
            responseData = new ResponseData();
        }
        super.render(responseData);
    }

    public void analyzeVoice(@RequestParam("audioData") MultipartFile[] file,@RequestParam(value = "format",required = false)String format) throws IOException {
        Map recognition = new HashMap();
        if (ArrayUtils.isNotEmpty(file)) {
            format = StringUtil.isEmpty(format)?"pcm":format;
//            String webPath = SpringContext.getWebPath();
//            String soundDir = webPath + "/temp_sound/";
//            File dir = new File(soundDir);
//            if (!dir.exists()){
//                dir.mkdir();
//            }
//            File sound = new File(dir.getAbsolutePath(),file[0].getName());
//            sound.createNewFile();
//            file[0].transferTo(sound);
            recognition = VoiceRecognition.recognition(file[0].getBytes(),format);
        }
        ResponseData responseData = new ResponseData(recognition);
        super.render(responseData);
    }
}
