package org.cb.brands.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BrandRs implements Serializable {

    @Serial
    private static final long serialVersionUID = -7043454689133028127L;

    private String id;

    private String name;

    private Set<String> categories;

    private String createdBy;

    private String createdOn;

    private String updatedBy;

    private String updatedOn;

}
