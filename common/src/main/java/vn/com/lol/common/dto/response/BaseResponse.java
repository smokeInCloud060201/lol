package vn.com.lol.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.com.lol.common.enums.ExceptionErrorCode;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BaseResponse <T> {
    private T data;
    private String errorMessage;
    private ExceptionErrorCode errorCode;
}