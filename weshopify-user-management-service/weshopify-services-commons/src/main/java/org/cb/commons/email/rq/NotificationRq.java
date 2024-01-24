package org.cb.commons.email.rq;

import lombok.Builder;
import lombok.Data;
import org.cb.commons.email.rs.EmailNameRs;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class NotificationRq implements Serializable {

    private Integer id;

    private EmailNameRs to;

    private String username;

    private String subject;

}
