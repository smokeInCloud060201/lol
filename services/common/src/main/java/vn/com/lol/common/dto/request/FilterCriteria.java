package vn.com.lol.common.dto.request;

import lombok.Builder;
import lombok.Getter;

import static vn.com.lol.common.constants.PagingConstant.DEFAULT_PAGE_INDEX;
import static vn.com.lol.common.constants.PagingConstant.DEFAULT_PAGE_SIZE;

@Builder
@Getter
public class FilterCriteria {

    @Builder.Default
    private int pageIndex = DEFAULT_PAGE_INDEX;

    @Builder.Default
    private int pageSize = DEFAULT_PAGE_SIZE;

    private String searchKey;

    private String sortField;

    @Builder.Default
    private boolean isASC = true;
}
