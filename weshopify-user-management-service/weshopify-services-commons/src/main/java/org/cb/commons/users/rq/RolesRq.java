package org.cb.commons.users.rq;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesRq implements Serializable {

    private Integer id;

    private String name;

    private List<PermissionsRq> permissions;

    private String provision;

}
