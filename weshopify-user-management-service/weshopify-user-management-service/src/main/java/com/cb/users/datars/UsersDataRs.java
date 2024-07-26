package com.cb.users.datars;

import com.cb.users.rs.UsersRs;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.commons.base.datars.BaseDataRs;

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
