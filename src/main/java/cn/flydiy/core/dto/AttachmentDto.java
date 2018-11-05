package cn.flydiy.core.dto;

import cn.flydiy.common.json.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by flying on 16-12-2.
 */
public class AttachmentDto extends BaseDto {

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -2299892744388731585L;

    private String subject;
    private String body;
    private List<MultipartFile> attachments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<MultipartFile> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MultipartFile> attachments) {
        this.attachments = attachments;
    }
}