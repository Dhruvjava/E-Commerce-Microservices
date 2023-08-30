package com.cb.users.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rq.PermissionsRq;
import com.cb.users.service.PermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
@Slf4j
public class PermissionsRest {

    @Autowired
    private PermissionsService permissionsService;

    @PostMapping
    public ResponseEntity<BaseDataRs> createPermissions(
            @RequestBody PermissionsRq rq
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [ /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.createPermission(rq));
        } catch (RuntimeException e) {
            log.error("Exception in RESTfull Services : [ /api/permissions] -> {0}", e);
            throw e;
        }
    }

}
