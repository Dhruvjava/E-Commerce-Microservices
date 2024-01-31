package org.cb.authn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.service.IAuthenticationService;
import org.cb.base.datars.BaseDataRs;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Override
    public BaseDataRs token(AuthenticationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing token(AuthenticationRq) -> ");
        }
        return null;
    }
}
