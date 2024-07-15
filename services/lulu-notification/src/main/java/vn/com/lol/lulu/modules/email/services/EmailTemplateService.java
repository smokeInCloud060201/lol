package vn.com.lol.lulu.modules.email.services;

import org.springframework.data.domain.Page;
import vn.com.lol.common.dto.request.SearchDTO;
import vn.com.lol.lulu.modules.email.dtos.request.UpsertEmailTemplateRequest;
import vn.com.lol.lulu.modules.email.dtos.response.EmailTemplateResponse;

public interface EmailTemplateService {
    EmailTemplateResponse createEmailTemplate(UpsertEmailTemplateRequest request);

    EmailTemplateResponse updateEmailTemplate(UpsertEmailTemplateRequest request, Long id);

    Page<EmailTemplateResponse> getAll(SearchDTO searchDTO);

    void deleteEmailTemplate(Long id);
}
