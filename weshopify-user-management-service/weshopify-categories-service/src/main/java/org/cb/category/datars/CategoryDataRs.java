package org.cb.category.datars;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.category.rs.CategoryRs;
import org.cb.commons.base.datars.BaseDataRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDataRs extends BaseDataRs {

    private CategoryRs category;

    public CategoryDataRs(String message, CategoryRs category) {
        super(message);
        this.category = category;
    }

    public CategoryDataRs(String message) {
        super(message);
    }
}
