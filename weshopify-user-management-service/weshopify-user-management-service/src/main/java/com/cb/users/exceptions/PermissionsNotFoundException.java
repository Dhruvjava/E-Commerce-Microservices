package com.cb.users.exceptions;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class PermissionsNotFoundException extends Exception{

    private String code;

    private String detail;
}
