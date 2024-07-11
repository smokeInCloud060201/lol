package vn.com.lol.lulu.modules.email.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.com.lol.lulu.modules.email.dtos.request.UpsertEmailTemplateRequest;
import vn.com.lol.lulu.modules.email.dtos.response.EmailTemplateResponse;
import vn.com.lol.lulu.modules.email.mapper.EmailTemplateMapper;
import vn.com.lol.lulu.modules.email.services.EmailTemplateService;

@Service
@RequiredArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplateResponse createEmailTemplate(UpsertEmailTemplateRequest request) {
        return null;
    }

    @Override
    public EmailTemplateResponse updateEmailTemplate(UpsertEmailTemplateRequest request, Long id) {
        return null;
    }

    @Override
    public Page<EmailTemplateResponse> getAll() {
        return null;
    }

    @Override
    public void deleteEmailTemplate(Long id) {

    }
}
