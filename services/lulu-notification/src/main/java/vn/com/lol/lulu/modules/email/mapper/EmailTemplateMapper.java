package vn.com.lol.lulu.modules.email.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import vn.com.lol.lulu.modules.email.dtos.request.UpsertEmailTemplateRequest;
import vn.com.lol.lulu.modules.email.dtos.response.EmailTemplateResponse;
import vn.com.lol.lulu.modules.email.entities.EmailTemplate;

import java.util.Random;

@Service
public class EmailTemplateMapper {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random random = new Random();


    public EmailTemplateResponse mapFromEntityToDTO9(EmailTemplate emailTemplate) {
        if (null == emailTemplate) {
            return null;
        }

        return EmailTemplateResponse.builder()
                .id(emailTemplate.getTemplateId())
                .templateId(emailTemplate.getTemplateId())
                .content(emailTemplate.getContent())
                .subject(emailTemplate.getSubject())
                .templateName(emailTemplate.getTemplateName())
                .build();
    }

    public EmailTemplate mapFromDTOToEntity(UpsertEmailTemplateRequest upsertEmailTemplateRequest) {
        EmailTemplate emailTemplate = new EmailTemplate();


        emailTemplate.setTemplateId(generateRandomString());
        emailTemplate.setTemplateName(upsertEmailTemplateRequest.getName());
        emailTemplate.setSubject(upsertEmailTemplateRequest.getSubject());
        emailTemplate.setContent(upsertEmailTemplateRequest.getContent());
        return emailTemplate;
    }

    public void copyFromDTOToExistEntity(UpsertEmailTemplateRequest request, EmailTemplate emailTemplate) {
        if (StringUtils.isNotBlank(request.getContent())) {
            emailTemplate.setContent(request.getContent());
        }
        if (StringUtils.isNotBlank(request.getName())) {
            emailTemplate.setTemplateName(request.getName());
        }
        if (StringUtils.isNotBlank(request.getSubject())) {
            emailTemplate.setSubject(request.getSubject());
        }
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        sb.append('-');

        for (int i = 0; i < 4; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        sb.append('-');

        for (int i = 0; i < 4; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
}
