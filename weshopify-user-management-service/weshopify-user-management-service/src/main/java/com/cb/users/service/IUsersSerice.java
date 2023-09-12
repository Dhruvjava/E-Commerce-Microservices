package com.cb.users.service;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.entity.Users;
import com.cb.users.rq.CreateUsersRq;
import com.cb.users.rq.UpdateUsersRq;

public interface IUsersSerice {

    public BaseDataRs createUsers(CreateUsersRq rq);

    public BaseDataRs updateUsers(UpdateUsersRq rq);

    public BaseDataRs findUser(int id);

    public BaseDataRs findUserByEmail(String email);

    public BaseDataRs deleteUsers(int id);

    public BaseDataRs findUsers();

    public BaseDataRs findUsers(int offSet, int limit);

    /***
     * Search Criteria yet to be decided
     * @return
     */
    public BaseDataRs searchUsers();

    /**
     * Assigining role to user is called provisioning
     * @param role
     * @return
     */
    public BaseDataRs provisioning(Users usersBO);

    /**
     * removing role to user is called deprovisioning
     * @param role
     * @return
     */
    public BaseDataRs deProvisioning(Users usersBO);

    public BaseDataRs enableUser(String role);

    public BaseDataRs unlockUser(String role);

}
