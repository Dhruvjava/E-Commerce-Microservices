package com.cb.users.rs;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionsRs implements Serializable {

    private Integer id;

    private String name;

}
