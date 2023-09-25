package com.cb.users.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.config.openapi.CustomApiResponses;
import com.cb.users.rq.RolesRq;
import com.cb.users.service.IRolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Slf4j
@Tag(name = "ROLES SERVICE")
public class RolesRest {

    @Autowired
    private IRolesService rolesService;

    @PostMapping()
    @Operation(summary = "CREATE ROLES", description = "Create Weshopify Roles")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> createRoles(
            @RequestBody RolesRq rq
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(rolesService.createRole(rq));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @PutMapping()
    @Operation(summary = "UPDATE ROLES", description = "Update Weshopify Roles")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> updateRoles(
            @RequestBody RolesRq rq
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(rolesService.updateRole(rq));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "FIND ROLES", description = "Find Weshopify Roles Using role id")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> findRoles(
            @PathVariable Integer id
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(rolesService.findRole(id));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE ROLES", description = "Delete Weshopify Roles using role id")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> deleteRoles(
            @PathVariable Integer id
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(rolesService.deleteRole(id));
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

    @GetMapping
    @Operation(summary = "RETRIEVE ROLES", description = "Retrieve Weshopify Roles")
    @CustomApiResponses
    public ResponseEntity<BaseDataRs> retrieveRoles(
    ) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFullServices [POST: /api/roles] ->");
        }
        try {
            return ResponseEntity.ok(rolesService.findAllRole());
        } catch (Exception e) {
            log.error("Exception in RESTFullServices [POST: /api/roles] -> {0}", e);
            throw e;
        }
    }

}
