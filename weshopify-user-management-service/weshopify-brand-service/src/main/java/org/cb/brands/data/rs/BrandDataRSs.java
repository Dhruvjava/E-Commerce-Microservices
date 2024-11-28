package org.cb.brands.data.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.brands.rs.BrandRs;
import org.cb.commons.base.datars.BaseDataRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrandDataRSs extends BaseDataRs {

    private List<BrandRs> brands;

    public BrandDataRSs(String message, List<BrandRs> brand) {
        super(message);
        this.brands = brand;
    }

    public BrandDataRSs(String message) {
        super(message);
    }

}
