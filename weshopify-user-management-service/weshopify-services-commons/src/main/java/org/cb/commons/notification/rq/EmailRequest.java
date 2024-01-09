package org.cb.commons.notification.rq;

import lombok.*;

@Data
@Builder
public class EmailRequest {

    private String email;

    private String userId;

    private int id;

}
