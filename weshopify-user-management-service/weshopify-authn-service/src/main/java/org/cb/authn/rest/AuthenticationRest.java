package org.cb.authn.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.service.IAuthenticationService;
import org.cb.base.datars.BaseDataRs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationRest {

    private final IAuthenticationService authService;

    @PostMapping("/token")
    public ResponseEntity<BaseDataRs> authenticate(@RequestBody AuthenticationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFull Services [POST : /api/auth/token ] -> ");
        }
        log.info("Authenticating User with given username : {}", rq.getUsername());

        return ResponseEntity.ok(authService.token(rq));
    }

}
