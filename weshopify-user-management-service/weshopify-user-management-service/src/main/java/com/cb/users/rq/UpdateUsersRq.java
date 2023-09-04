package com.cb.users.rq;

import com.cb.users.rs.RolesRs;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUsersRq {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String userid;

    private String mobile;

    private boolean enabled;

    private boolean locked;

    private List<RolesRs> role;
}
