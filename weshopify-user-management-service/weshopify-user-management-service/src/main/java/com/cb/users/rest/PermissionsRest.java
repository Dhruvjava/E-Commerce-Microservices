package com.cb.users.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.service.PermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@Slf4j
public class PermissionsRest {

    @Autowired
    private PermissionsService permissionsService;

    @PostMapping
    public ResponseEntity<BaseDataRs> createPermissions(
            @RequestBody CreatePermissionsRq rq
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

    @PutMapping
    public ResponseEntity<BaseDataRs> updatePermissions(
            @RequestBody UpdatePermissionsRq rq
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [POST: /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.updatePermission(rq));
        } catch (RuntimeException e) {
            log.error("Exception in RESTfull Services : [PUT: /api/permissions] -> {0}", e);
            throw e;
        }
    }

}
