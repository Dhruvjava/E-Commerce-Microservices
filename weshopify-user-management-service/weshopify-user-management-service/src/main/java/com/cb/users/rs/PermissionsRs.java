package com.cb.users.rs;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionsRs implements Serializable {

    private int id;

    private String name;

}
