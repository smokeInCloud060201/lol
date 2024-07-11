package vn.com.lol.lulu.modules.email.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.constants.ControllerPathConstant;
import vn.com.lol.common.controller.BaseController;
import vn.com.lol.common.dto.response.BaseResponse;
import vn.com.lol.common.mapper.BaseResponseMapper;
import vn.com.lol.common.validations.CreateValidation;
import vn.com.lol.lulu.modules.email.dtos.request.UpsertEmailTemplateRequest;
import vn.com.lol.lulu.modules.email.dtos.response.EmailTemplateResponse;
import vn.com.lol.lulu.modules.email.services.EmailTemplateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerPathConstant.API_V1_PREFIX_BASE_PATH + "/email-templates")
public class EmailTemplatePrivateController extends BaseController {

    private final EmailTemplateService emailTemplateService;

    @PostMapping
    public BaseResponse<EmailTemplateResponse> createEmailTemplate(@Validated(CreateValidation.class) @RequestBody UpsertEmailTemplateRequest request) {
        return BaseResponseMapper.of(emailTemplateService.createEmailTemplate(request));
    }

    @PutMapping("/{id}")
    public BaseResponse<EmailTemplateResponse> updateEmailTemplate(@Valid @RequestBody UpsertEmailTemplateRequest request, @PathVariable("id") Long id) {
        return BaseResponseMapper.of(emailTemplateService.updateEmailTemplate(request, id));
    }

    @GetMapping("/search")
    public BaseResponse<List<EmailTemplateResponse>> getAllEmailTemplate() {
        return BaseResponseMapper.ofPageable(emailTemplateService.getAll());
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteEmailTemplate(@PathVariable("id") Long id) {
        emailTemplateService.deleteEmailTemplate(id);
        return BaseResponseMapper.of(null);
    }
}
