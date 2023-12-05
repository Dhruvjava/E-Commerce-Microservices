package com.cb.users.rs;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UsersRs implements Serializable {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String userid;

    private String mobile;

    private boolean enabled;

    private boolean locked;

    private RolesRs role;
}
