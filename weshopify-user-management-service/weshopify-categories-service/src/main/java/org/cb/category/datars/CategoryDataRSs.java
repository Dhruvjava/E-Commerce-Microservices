package org.cb.category.datars;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.category.rs.CategoryRs;
import org.cb.commons.base.datars.BaseDataRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDataRSs extends BaseDataRs {

    private List<CategoryRs> categories;

    public CategoryDataRSs(String message, List<CategoryRs> categories) {
        super(message);
        this.categories = categories;
    }

    public CategoryDataRSs(String message) {
        super(message);
    }
}
