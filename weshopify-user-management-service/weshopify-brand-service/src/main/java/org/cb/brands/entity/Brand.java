package org.cb.brands.entity;

import lombok.*;
import org.cb.base.bo.BaseBO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Document("brands")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand extends BaseBO {

    @Serial
    private static final long serialVersionUID = 8973764831122361288L;

    @Indexed(unique = true)
    private String name;

    private Set<String> categories;

//    private String createdby;
//
//    private LocalDateTime createdon;
//
//    private String updatedby;
//
//    private LocalDateTime updatedon;
}
