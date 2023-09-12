package com.cb.users.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.config.openapi.CustomApiResponses;
import com.cb.users.rq.CreatePermissionsRq;
import com.cb.users.rq.UpdatePermissionsRq;
import com.cb.users.service.IPermissionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@Slf4j
@Tag(name = "PERMISSIONS SERVICE")
public class PermissionsRest {

    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping
    @Operation(summary = "CREATE PERMISSIONS", description = "Create Weshopify Permissions")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> createPermissions(@RequestBody CreatePermissionsRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESFull Services : [ /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.createPermission(rq));
        } catch (RuntimeException e) {
            log.error("Exception in RESFull Services : [ /api/permissions] -> {0}", e);
            throw e;
        }
    }

    @PutMapping
    @Operation(summary = "UPDATE PERMISSIONS", description = "Update Weshopify Permissions")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> updatePermissions(@RequestBody UpdatePermissionsRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESFull Services : [POST: /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.updatePermission(rq));
        } catch (RuntimeException e) {
            log.error("Exception in RESFull Services : [PUT: /api/permissions] -> {0}", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "FIND PERMISSIONS", description = "Find Weshopify Permissions using id")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> findPermissions(@PathVariable int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESFull Services : [GET: /api/permissions/{id}] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.findPermission(id));
        } catch (RuntimeException e) {
            log.error("Exception in RESFull Services : [GET: /api/permissions/{id}] -> {0}", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE PERMISSIONS", description = "Delete Weshopify Permissions using id")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> deletePermissions(@PathVariable int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESFull Services : [DELETE: /api/permissions/{id}] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.deletePermission(id));
        } catch (RuntimeException e) {
            log.error("Exception in RESFull Services : [DELETE: /api/permissions/{id}] -> {0}", e);
            throw e;
        }
    }

    @GetMapping()
    @Operation(summary = "RETRIEVE ALL PERMISSIONS", description = "Retrieve All Weshopify Permissions")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> findPermissions() {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESFull Services : [GET: /api/permissions] ->");
        }
        try {
            return ResponseEntity.ok(permissionsService.findAllPermission());
        } catch (RuntimeException e) {
            log.error("Exception in RESFull Services : [GET: /api/permissions] -> {0}", e);
            throw e;
        }
    }

}
