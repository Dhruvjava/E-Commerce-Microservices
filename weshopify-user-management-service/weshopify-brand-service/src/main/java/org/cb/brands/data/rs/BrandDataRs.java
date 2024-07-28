package org.cb.brands.data.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.brands.rs.BrandRs;
import org.cb.commons.base.datars.BaseDataRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrandDataRs extends BaseDataRs {

    private BrandRs brand;

    public BrandDataRs(String message, BrandRs brand) {
        super(message);
        this.brand = brand;
    }

    public BrandDataRs(String message) {
        super(message);
    }

}
