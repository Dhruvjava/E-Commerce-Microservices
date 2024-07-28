package org.cb.authn.rq;

import lombok.*;
import org.cb.commons.base.rq.BaseRq;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wso2TokenRq extends BaseRq {

    private String grant_type;

    private String username;
    
    private String password;

    private String scope;

}
