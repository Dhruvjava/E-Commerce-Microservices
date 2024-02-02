package org.cb.authn.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.authn.clients.Wso2IamClient;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.service.IAuthenticationService;
import org.cb.base.datars.BaseDataRs;
import org.cb.utils.Utils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final Wso2IamClient wso2IamClient;

    @Override
    public BaseDataRs token(AuthenticationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing token(AuthenticationRq) -> ");
        }
        try {
            String username = Utils.getValidString(rq.getUsername());
            String password = Utils.getValidString(rq.getPassword());
        } catch (Exception e) {
            log.error("Exception in token(AuthenticationRq) -> {0}", e);
        }
        return null;
    }
}
