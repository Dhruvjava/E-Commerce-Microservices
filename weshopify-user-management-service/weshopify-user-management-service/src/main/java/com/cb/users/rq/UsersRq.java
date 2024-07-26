package com.cb.users.rq;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersRq implements Serializable {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String userid;

    private String mobile;

    private boolean enabled;

    private boolean locked;

    private UserRoleRq role;
}
