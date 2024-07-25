package org.cb.category.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRq implements Serializable {

    private Integer id;

    private String name;

    private String alias;

    private Integer parentCategory;

    private String image;

    private boolean enabled = false;

}
