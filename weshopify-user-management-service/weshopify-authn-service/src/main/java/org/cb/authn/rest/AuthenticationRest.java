package org.cb.authn.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cb.authn.rq.AuthenticationRq;
import org.cb.authn.service.IAuthenticationService;
import org.cb.commons.base.datars.BaseDataRs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationRest {

    private final IAuthenticationService authService;

    @PostMapping("/signin")
    public ResponseEntity<BaseDataRs> authenticate(@RequestBody AuthenticationRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFull Services [POST : /api/auth/signin ] -> ");
        }
        log.info("Authenticating User with given username : {}", rq.getUsername());

        return ResponseEntity.ok(authService.token(rq));
    }

    @GetMapping("/logout")
    public ResponseEntity<BaseDataRs> logout(HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services [GET : /api/auth/logout] -> ");
        }
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(accessToken);
        return ResponseEntity.ok(authService.logout(accessToken));
    }

}
