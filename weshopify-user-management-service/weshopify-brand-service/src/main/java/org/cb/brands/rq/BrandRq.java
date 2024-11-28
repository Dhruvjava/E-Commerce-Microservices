package org.cb.brands.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.brands.constants.ErrorCodes;
import org.cb.commons.base.rq.BaseRq;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serial;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandRq extends BaseRq {

    @Serial
    private static final long serialVersionUID = 6636661503198045979L;

    private String id;

    @NotBlank(message=ErrorCodes.EC_REQUIRED_BRAND_NAME)
    private String name;

    @NotNull(message = ErrorCodes.EC_REQUIRED_BRAND_CATEGORY)
    private Set<String> categories;

}
