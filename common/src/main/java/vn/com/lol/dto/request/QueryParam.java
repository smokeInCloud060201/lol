package vn.com.lol.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QueryParam {
    private String key;
    private Object value;
}
