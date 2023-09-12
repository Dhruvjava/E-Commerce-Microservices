package com.cb.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleid")
    private int id;

    @Column(nullable = false, unique = true, updatable = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles", fetch = FetchType.EAGER)
    private List<RoleToPermission> roleToPermissions;

    private String createdBy;

    private LocalDateTime createdOn;

    private String updatedBy;

    private LocalDateTime updatedOn;
}
