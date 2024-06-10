package vn.com.lol.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import vn.com.lol.dto.response.BaseResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponseMapper {

    public static <T> BaseResponse<T> of(T data) {
        return BaseResponse.<T>builder()
                .data(data)
                .build();
    }
}
