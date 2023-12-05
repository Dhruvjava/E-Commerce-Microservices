package org.cb.commons.users.rq;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleRq implements Serializable {

    private Integer id;

    private String name;
    
    private String operation;
}
