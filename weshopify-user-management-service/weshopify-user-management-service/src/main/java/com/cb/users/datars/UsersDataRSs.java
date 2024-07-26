package com.cb.users.datars;

import com.cb.users.rs.UsersRs;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UsersDataRSs extends BaseDataRs {

    private List<UsersRs> user;

    public UsersDataRSs(String message, List<UsersRs> user) {
        super(message);
        this.user = user;
    }

}
