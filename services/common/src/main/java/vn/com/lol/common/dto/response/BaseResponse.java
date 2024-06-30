package vn.com.lol.common.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.com.lol.common.enums.ExceptionErrorCode;

@Builder
@Getter
public class BaseResponse <T> {
    @JsonProperty("data")
    private T data;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_code")
    private ExceptionErrorCode errorCode;
}
