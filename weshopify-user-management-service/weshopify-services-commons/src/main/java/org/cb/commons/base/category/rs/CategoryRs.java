package org.cb.commons.base.category.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryRs {

  private Integer id;

  private String name;

  private String alias;

  private int parentCategory;

  private String image;

  private boolean enabled;

  private String createdBy;

  private String createdOn;

  private String updatedBy;

  private String updatedOn;

}
