package com.cb.users.datars;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rs.PermissionsRs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionsDataRs extends BaseDataRs {

    private PermissionsRs permission;

    public PermissionsDataRs(String message, PermissionsRs permission) {
        super(message);
        this.permission = permission;
    }

}
