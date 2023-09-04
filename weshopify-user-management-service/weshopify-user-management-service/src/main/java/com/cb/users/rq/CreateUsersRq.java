package com.cb.users.rq;

import com.cb.users.entity.Roles;
import com.cb.users.rs.RolesRs;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUsersRq implements Serializable {

    private String firstname;

    private String lastname;

    private String email;

    private String userid;

    private String mobile;

    private boolean enabled;

    private boolean locked;

    private List<RolesRs> role;
}
