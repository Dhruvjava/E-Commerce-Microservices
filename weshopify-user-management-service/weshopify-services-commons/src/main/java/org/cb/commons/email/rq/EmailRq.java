package org.cb.commons.email.rq;

import java.util.Collections;
import java.util.List;

import lombok.*;
import org.cb.commons.base.rq.BaseRq;
import org.cb.commons.email.rs.EmailNameRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRq extends BaseRq {

    private static final long serialVersionUID = -2255276665946007648L;

    private String subject;

    private String messageText;

    private EmailNameRs toEmailAddr;

    private List<EmailNameRs> toEmailAddrs = Collections.emptyList();

    private List<EmailNameRs> ccEmailAddrs = Collections.emptyList();

    private List<AttachmentRqVM> attachments = Collections.emptyList();

}
