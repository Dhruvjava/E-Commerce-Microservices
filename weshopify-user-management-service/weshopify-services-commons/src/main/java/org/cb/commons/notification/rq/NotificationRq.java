package org.cb.commons.notification.rq;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class NotificationRq implements Serializable {

    private List<EmailRequest> to;

    private String subject;

    private String contentType;

    private int statusCode;

}
