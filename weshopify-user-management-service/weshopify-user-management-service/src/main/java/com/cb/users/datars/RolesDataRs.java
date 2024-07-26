package com.cb.users.datars;

import com.cb.users.rs.RolesRs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.commons.base.datars.BaseDataRs;

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
