package com.cb.users.rs;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesRs implements Serializable {

    private int id;

    private String name;

}
