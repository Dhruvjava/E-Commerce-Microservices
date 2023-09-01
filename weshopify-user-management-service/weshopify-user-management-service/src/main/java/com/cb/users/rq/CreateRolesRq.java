package com.cb.users.rq;

import com.cb.users.rs.PermissionsRs;
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

    private List<PermissionsRs> permissions;

}
