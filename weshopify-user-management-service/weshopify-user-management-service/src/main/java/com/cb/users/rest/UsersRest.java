package com.cb.users.rest;

import com.cb.base.data.rs.BaseDataRs;
import com.cb.users.rq.UsersRq;
import com.cb.users.service.IUsersSerice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Tag(name = "USERS SERVICE")
public class UsersRest {

    @Autowired
    private IUsersSerice usersSerice;

    @PostMapping()
    @Operation(summary = "CREATE USERS", description = "Create Weshopify Users")
    public ResponseEntity<BaseDataRs> createUser(@RequestBody UsersRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFull Services [ GET : /api/users] ->");
        }
        try {
            return ResponseEntity.ok(usersSerice.createUsers(rq));
        } catch (Exception e) {
            log.error("Exception in RESTFull Services [ GET : /api/users] -> {0}", e);
            throw e;
        }
    }

    @GetMapping()
    @Operation(summary = "RETRIEVE USERS", description = "Retrieve Weshopify Users")
    public ResponseEntity<BaseDataRs> findAllUser() {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFull Services [ GET : /api/users] ->");
        }
        try {
            return ResponseEntity.ok(usersSerice.findUsers());
        } catch (Exception e) {
            log.error("Exception in RESTFull Services [ GET : /api/users] -> {0}", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "RETRIEVE USERS BY ID", description = "Retrieve Weshopify Users By Id")
    public ResponseEntity<BaseDataRs> findUser(@PathVariable int id) {
        if (log.isDebugEnabled()) {
            log.debug("Executing RESTFull Services [ GET : /api/users] ->");
        }
        try {
            return ResponseEntity.ok(usersSerice.findUser(id));
        } catch (Exception e) {
            log.error("Exception in RESTFull Services [ GET : /api/users] -> {0}", e);
            throw e;
        }
    }

}
