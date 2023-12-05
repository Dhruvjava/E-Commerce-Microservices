package org.cb.commons.users.datars;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rs.UsersRs;
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
public class UsersDataRs extends BaseDataRs {

    private UsersRs user;

    public UsersDataRs(String message, UsersRs user) {
        super(message);
        this.user = user;
    }

    public UsersDataRs(String message) {
        super(message);
    }

}
