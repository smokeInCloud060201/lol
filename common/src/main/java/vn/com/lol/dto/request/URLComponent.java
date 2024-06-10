package vn.com.lol.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import vn.com.lol.dto.request.QueryParam;

import java.util.List;

@SuperBuilder
@Getter
public class URLComponent {
    @Builder.Default
    private String scheme = "https";
    private String host;
    private String path;

    @Singular("queryParam")
    private List<QueryParam> queryParams;
}
