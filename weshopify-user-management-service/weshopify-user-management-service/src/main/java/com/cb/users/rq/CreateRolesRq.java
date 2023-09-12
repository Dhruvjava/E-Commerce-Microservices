package com.cb.users.rq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateRolesRq implements Serializable {

    private String name;

    private List<UpdatePermissionsRq> permissions;

}
