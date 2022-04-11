package arch.component;

import arch.dto.AbstractPagination;
import org.springframework.data.domain.Page;

public final class PaginationUtils {

    public static final String DEFAULT_PAGE_START = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";

    public static void setResponsePagination(Page<?> atomicPage, AbstractPagination<?> responsePage){
        responsePage.setPageSize(atomicPage.getSize());
        responsePage.setPageNumber(atomicPage.getNumber());
        responsePage.setTotalPages(atomicPage.getTotalPages());
        responsePage.setTotalElements(atomicPage.getTotalElements());
    }
}
