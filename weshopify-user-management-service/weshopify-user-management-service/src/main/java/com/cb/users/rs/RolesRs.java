package com.cb.users.rs;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesRs implements Serializable {

    private Integer id;

    private String name;

    private List<PermissionsRs> permissions;

}
