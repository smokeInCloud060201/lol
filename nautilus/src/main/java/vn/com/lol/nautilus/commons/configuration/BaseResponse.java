package vn.com.lol.nautilus.commons.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BaseResponse<T> {

    public BaseResponse() {
    }

    public BaseResponse(T data, String errorMessage, String errorCode) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    @JsonProperty("data")
    private T data;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_code")
    private String errorCode;

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
