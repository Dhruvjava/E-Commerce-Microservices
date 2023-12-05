package org.cb.commons.users.datars;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.entity.Users;
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
public class UserProvisioningDataRs extends BaseDataRs {

    Users users;

    public UserProvisioningDataRs(String message, Users users) {
        super(message);
        this.users = users;
    }
}
