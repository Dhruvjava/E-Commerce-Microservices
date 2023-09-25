package com.cb.users.mapper;

import com.cb.users.entity.Users;
import com.cb.users.rq.UsersRq;
import com.cb.users.rs.UsersRs;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class UsersMapper {

    private UsersMapper() {
    }

    public static Users mapToUsers(UsersRq rq, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing mapToUsers(UsersRq rq)");
        }
        try {
//            Users users = mapper.map(rq, Users.class);
            return mapper.map(rq, Users.class);
        } catch (Exception e) {
            log.error("Exception in mapToUsers(UsersRq rq) -> {0}", e);
            throw e;
        }
    }

    public static UsersRs maptoUsers(Users users, ModelMapper mapper) {
        if (log.isDebugEnabled()) {
            log.debug("Executing maptoUsers(Users users, ModelMapper mapper) ->");
        }
        try {
            UsersRs usersRs = mapper.map(users, UsersRs.class);
//            users
            return usersRs;
        } catch (Exception e) {
            log.error("Exception in maptoUsers(Users users, ModelMapper mapper) -> {0}", e);
            throw e;
        }
    }
}
