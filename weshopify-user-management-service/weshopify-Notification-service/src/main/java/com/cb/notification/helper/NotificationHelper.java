package com.cb.notification.helper;

import com.cb.Messages;
import com.cb.base.rs.ErrorRs;
import com.cb.constants.ErrorCodes;
import com.cb.util.Utils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.email.rq.NotificationRq;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class NotificationHelper {
    public static List<ErrorRs> validateSendNotification(NotificationRq rq, Messages messages) {
        if (log.isDebugEnabled()) {
            log.debug("Executing validateSendNotification(NotificationRq) -> ");
        }
        try {
            List<ErrorRs> errors = new ArrayList<>();
            if (rq.getTo() == null) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_TOEMAIL);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_NOTIFICATION_TOEMAIL,
                                messages));
            }
            if (Utils.isEmpty(rq.getSubject())) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_SUBJECT);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_NOTIFICATION_SUBJECT,
                                messages));
            }
            if (Utils.isEmpty(rq.getUsername())) {
                log.error(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERNAME);
                errors.add(Utils.populateErrorRSs(ErrorCodes.EC_REQUIRED_NOTIFICATION_USERNAME,
                                messages));
            }
            return errors;
        } catch (Exception e) {
            log.error("Exception in validateSendNotification(NotificationRq) -> ", e);
            throw e;
        }
    }

    public static String prepareVerifyEmailTempate(Configuration templateConfig, Messages messages,
                    Map<String, Object> model) throws TemplateException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("Executing prepareVerifyEmailTempate(Messages, Map<String, Object>) -> ");
        }
        try {
            String templateName = messages.getEmailProperty("email.signup.template.name");
            Template template = templateConfig.getTemplate(templateName);
//            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            return null;
        } catch (Exception e) {
            log.error("Exception in prepareVerifyEmailTempate(Messages, Map<String, Object>) -> ",
                            e);
            throw e;
        }
    }
}
