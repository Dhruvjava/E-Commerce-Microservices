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
    public ResponseEntity<BaseDataRs> createPermissions(@RequestBody CreatePermissionsRq rq) {
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
    public ResponseEntity<BaseDataRs> updatePermissions(@RequestBody UpdatePermissionsRq rq) {
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

    @GetMapping("/{id}")
    public ResponseEntity<BaseDataRs> updatePermissions(@PathVariable int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [GET: /api/permissions/{id}] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.findPermission(id));
        } catch (RuntimeException e) {
            log.error("Exception in RESTfull Services : [GET: /api/permissions/{id}] -> {0}", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseDataRs> deletePermissions(@PathVariable int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [DELETE: /api/permissions/{id}] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.deletePermission(id));
        } catch (RuntimeException e) {
            log.error("Exception in RESTfull Services : [DELETE: /api/permissions/{id}] -> {0}", e);
            throw e;
        }
    }

    @GetMapping()
    public ResponseEntity<BaseDataRs> findPermissions() {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTfull Services : [GET: /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.findAllPermission());
        } catch (RuntimeException e) {
            log.error("Exception in RESTfull Services : [GET: /api/permissions] -> {0}", e);
            throw e;
        }
    }

}
