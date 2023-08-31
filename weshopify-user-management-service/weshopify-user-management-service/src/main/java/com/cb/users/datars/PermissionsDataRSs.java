package com.cb.users.datars;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rs.PermissionsRs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsDataRSs extends BaseDataRs {

    private List<PermissionsRs> permissions = Collections.emptyList();

    public PermissionsDataRSs(String message, List<PermissionsRs> permissions) {
        super(message);
        this.permissions = permissions;
    }

    public PermissionsDataRSs(String message) {
        super(message);
    }

}
