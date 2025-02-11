package com.cb.users.datars;

import com.cb.users.rs.RolesRs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.commons.base.datars.BaseDataRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolesDataRSs extends BaseDataRs {

    private List<RolesRs> roles;

    public RolesDataRSs(String message, List<RolesRs> roles) {
        super(message);
        this.roles = roles;
    }

    public RolesDataRSs(String message) {
        super(message);
    }

}
