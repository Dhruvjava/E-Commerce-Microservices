package org.cb.category.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.category.constants.ErrorCodes;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRq implements Serializable {

    private Integer id;

//    @NotNull(message = ErrorCodes.EC_REQUIRED_CATEGORY_NAME)
    @NotBlank(message = ErrorCodes.EC_REQUIRED_CATEGORY_NAME)
    private String name;

//    @NotNull(message = ErrorCodes.EC_REQUIRED_CATEGORY_ALIAS)
    @NotBlank(message = ErrorCodes.EC_REQUIRED_CATEGORY_ALIAS)
    private String alias;

    private Integer parentCategory;

    private String image;

    private boolean enabled = false;

}
