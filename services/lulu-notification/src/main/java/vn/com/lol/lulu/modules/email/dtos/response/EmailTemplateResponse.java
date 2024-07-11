package vn.com.lol.lulu.modules.email.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailTemplateResponse {
    private String id;
    private String templateId;
    private String templateName;
    private String subject;
    private String content;
}
