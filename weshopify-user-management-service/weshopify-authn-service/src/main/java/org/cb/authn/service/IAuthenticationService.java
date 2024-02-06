package org.cb.authn.service;

import org.cb.authn.rq.AuthenticationRq;
import org.cb.base.datars.BaseDataRs;

public interface IAuthenticationService {

    public BaseDataRs token(AuthenticationRq rq);

    public BaseDataRs logout(String accessToken);

}
