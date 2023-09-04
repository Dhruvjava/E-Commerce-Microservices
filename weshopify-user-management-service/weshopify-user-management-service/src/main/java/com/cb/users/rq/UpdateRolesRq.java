package com.cb.users.rq;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRolesRq implements Serializable {

    private Integer id;

    private String name;

}
