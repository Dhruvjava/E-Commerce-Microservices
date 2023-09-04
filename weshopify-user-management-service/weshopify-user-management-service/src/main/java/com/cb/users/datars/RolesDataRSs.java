package com.cb.users.datars;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rs.RolesRs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolesDataRs extends BaseDataRs {

    private RolesRs roles;

    public RolesDataRs(String message, RolesRs roles) {
        super(message);
        this.roles = roles;
    }

    public RolesDataRs(String message) {
        super(message);
    }

}
