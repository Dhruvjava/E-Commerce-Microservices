package org.cb.commons.email.rq;

import lombok.*;
import org.springframework.core.io.InputStreamSource;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentRqVM {

    private InputStreamSource byteSource;

    private String name;

    private String type;

    private String contentType;

}
