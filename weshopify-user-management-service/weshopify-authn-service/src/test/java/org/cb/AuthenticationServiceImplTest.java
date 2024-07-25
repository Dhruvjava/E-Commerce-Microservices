package org.cb;

import org.cb.authn.datars.Wso2TokenDataRs;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.service.IAuthenticationService;
import org.cb.base.datars.BaseDataRs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImplTest extends WeshopifyAuthnServiceApplicationTests {

    @Autowired
    private IAuthenticationService service;

    private String accessToken = null;

    @Test
    @Order(1)
    @DisplayName("USER AUTHENTICATION")
    public void authenticate() {
        AuthenticationRq rq =
                        AuthenticationRq.builder().username("admin").password("admin").build();
        BaseDataRs baseDataRs = service.token(rq);
        Wso2TokenDataRs wso2TokenDataRs = (Wso2TokenDataRs) baseDataRs;
        accessToken = wso2TokenDataRs.getToken().getAccessToken();
        Assertions.assertNotNull(wso2TokenDataRs);
        Assertions.assertNotNull(accessToken);

    }

    @Test
    @Order(2)
    @DisplayName("USER LOGOUT")
    public void logout() {
        service.logout(accessToken);
    }


}
