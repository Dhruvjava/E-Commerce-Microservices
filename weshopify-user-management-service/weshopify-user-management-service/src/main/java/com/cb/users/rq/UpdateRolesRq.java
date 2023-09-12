package com.cb.users.rq;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRolesRq implements Serializable {

    private Integer id;

    private String name;

    private List<UpdatePermissionsRq> permissions;

}
