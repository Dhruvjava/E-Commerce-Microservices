package org.cb.base.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.repository.Update;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7019426856767315736L;

    @Id
    private String id;

    @CreatedBy
    private String createdby;

    @CreatedDate
    private LocalDateTime createdon;

    @LastModifiedBy
    private String updatedby;

    @LastModifiedDate
    private LocalDateTime updatedon;

}
