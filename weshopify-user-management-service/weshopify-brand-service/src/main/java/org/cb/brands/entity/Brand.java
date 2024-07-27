package org.cb.brands.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Document("brands")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand implements Serializable {

    @Serial
    private static final long serialVersionUID = -3228412039681957830L;

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private Map<Integer, String> categories;

    private String createdby;

    private LocalDateTime createdon;

    private String updatedby;

    private LocalDateTime updatedon;
}
