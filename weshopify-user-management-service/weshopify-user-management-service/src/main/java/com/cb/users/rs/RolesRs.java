package com.cb.users.rs;

import com.cb.users.entity.Permissions;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serial;
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
