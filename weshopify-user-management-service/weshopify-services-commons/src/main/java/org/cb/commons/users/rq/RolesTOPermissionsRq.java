package org.cb.commons.users.rq;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesTOPermissionsRq {

    private Integer id;

    private String name;
}
