package org.cb.authn.datars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.authn.rs.Wso2TokenRs;
import org.cb.base.datars.BaseDataRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Wso2TokenDataRs extends BaseDataRs {

    private Wso2TokenRs token;

    public Wso2TokenDataRs(String message, Wso2TokenRs token) {
        super(message);
        this.token = token;
    }

    public Wso2TokenDataRs(String message) {
        super(message);
    }
}
