package vn.com.lol.lulu.modules.email.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.com.lol.common.validations.CreateValidation;

@Getter
@NoArgsConstructor
public class UpsertEmailTemplateRequest {

    @NotBlank(groups = CreateValidation.class, message = "Email template name should not be blank")
    private String name;

    @NotBlank(groups = CreateValidation.class, message = "Email template subject should not be blank")
    private String subject;

    @NotBlank(groups = CreateValidation.class, message = "Email template content should not be blank")
    private String content;
}
