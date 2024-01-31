package org.cb.authn.rq;

import lombok.*;
import org.cb.base.rq.BaseRq;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRq extends BaseRq {

    private String username;

    private String password;

}
