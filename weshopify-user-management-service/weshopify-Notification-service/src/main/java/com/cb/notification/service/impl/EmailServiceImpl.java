package com.cb.notification.service.impl;

import com.cb.notification.service.IEmailService;
import com.cb.util.Utils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.cb.commons.email.rq.AttachmentRqVM;
import org.cb.commons.email.rq.EmailRq;
import org.cb.commons.email.rs.EmailNameRs;
import org.cb.constants.StringConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.from.email}")
    private String FROM_EMAIL;

    @Value("${mail.from.name}")
    private String FROM_NAME;

    @Override
    public synchronized void sendHtmlEmail(EmailRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing sendHtmlEmail(EmailRq) ->");
        }
        try {
            if (null == rq) {
                log.error("EmailRqVM is NULL");
                return;
            }
            javaMailSender.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage)
                                throws MessagingException, UnsupportedEncodingException {
                    List<AttachmentRqVM> attachmentRqs = rq.getAttachments();
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    helper.setFrom(new InternetAddress(FROM_EMAIL, FROM_NAME));
                    if (null != rq.getToEmailAddr()) {
                        helper.setTo(new InternetAddress(rq.getToEmailAddr().getEmail(),
                                        rq.getToEmailAddr().getName()));
                    }
                    if (Utils.isNotEmpty(rq.getToEmailAddrs())) {
                        List<InternetAddress> toAddrs = new ArrayList<InternetAddress>();
                        for (EmailNameRs toAddr : rq.getToEmailAddrs()) {
                            if ((null == toAddr) || (Utils.isEmpty(toAddr.getEmail()))) {
                                continue;
                            }

                            if (Utils.isEmpty(toAddr.getName())) {
                                toAddrs.add(new InternetAddress(toAddr.getEmail(), toAddr.getEmail()));
                            } else {
                                toAddrs.add(new InternetAddress(toAddr.getEmail(), toAddr.getName()));
                            }
                        }
                        helper.setTo(toAddrs.toArray(new InternetAddress[toAddrs.size()]));
                    }
                    if (Utils.isNotEmpty(rq.getCcEmailAddrs())) {
                        List<InternetAddress> ccAddrs = new ArrayList<InternetAddress>();
                        for (EmailNameRs ccAddr : rq.getCcEmailAddrs()) {
                            if ((null == ccAddr) || (Utils.isEmpty(ccAddr.getEmail()))) {
                                continue;
                            }
                            if (Utils.isEmpty(ccAddr.getName())) {
                                ccAddrs.add(new InternetAddress(ccAddr.getEmail(), ccAddr.getEmail()));
                            } else {
                                ccAddrs.add(new InternetAddress(ccAddr.getEmail(), ccAddr.getName()));
                            }
                        }
                        helper.setCc(ccAddrs.toArray(new InternetAddress[ccAddrs.size()]));
                    }
                    helper.setSubject(rq.getSubject());
                    helper.setText(rq.getMessageText(), true);
                    if (Utils.isNotEmpty(attachmentRqs)) {
                        for (AttachmentRqVM attachmentRqVM : attachmentRqs) {
                            if (Utils.isEmpty(attachmentRqVM.getContentType())) {
                                helper.addAttachment(attachmentRqVM.getName() + attachmentRqVM.getType(),
                                                attachmentRqVM.getByteSource());
                            } else {
                                helper.addAttachment(attachmentRqVM.getName() + attachmentRqVM.getType(),
                                                attachmentRqVM.getByteSource(), attachmentRqVM.getContentType());
                            }
                        }
                    }
                }
            });
            logSentMailDetails(rq);
        } catch (Exception e) {
            log.error("Exception in sendHtmlEmail(EmailRqVM) -> {} " + e);
            throw e;
        }
    }

    private void logSentMailDetails(EmailRq rqVM) {

        try {
            if (null == rqVM) {
                log.error("EmailRqVM is NULL");
                return;
            }
            log.info("SENT MAIL DETAILS - START");
            log.info("SUBJECT - " + rqVM.getSubject());
            if ((null != rqVM.getToEmailAddr()) || (Utils.isNotEmpty(rqVM.getToEmailAddrs()))) {
                StringBuffer toEmailAddrs = new StringBuffer();
                if (null != rqVM.getToEmailAddr()) {
                    toEmailAddrs.append(rqVM.getToEmailAddr().getName());
                    toEmailAddrs.append(StringConstants.LESS_THAN);
                    toEmailAddrs.append(rqVM.getToEmailAddr().getEmail());
                    toEmailAddrs.append(StringConstants.GREATER_THAN);
                    toEmailAddrs.append(StringConstants.SEMICOLON);
                }
                if (Utils.isNotEmpty(rqVM.getToEmailAddrs())) {
                    for (EmailNameRs emailNameVM : rqVM.getToEmailAddrs()) {
                        toEmailAddrs.append(emailNameVM.getName());
                        toEmailAddrs.append(StringConstants.LESS_THAN);
                        toEmailAddrs.append(emailNameVM.getEmail());
                        toEmailAddrs.append(StringConstants.GREATER_THAN);
                        toEmailAddrs.append(StringConstants.SEMICOLON);
                    }
                }
                if (toEmailAddrs.length() > 0) {
                    log.info("TO - " + toEmailAddrs);
                }
            }
            if (Utils.isNotEmpty(rqVM.getCcEmailAddrs())) {
                StringBuffer ccEmailAddrs = new StringBuffer();
                for (EmailNameRs emailNameVM : rqVM.getToEmailAddrs()) {
                    ccEmailAddrs.append(emailNameVM.getName());
                    ccEmailAddrs.append(StringConstants.LESS_THAN);
                    ccEmailAddrs.append(emailNameVM.getEmail());
                    ccEmailAddrs.append(StringConstants.GREATER_THAN);
                    ccEmailAddrs.append(StringConstants.SEMICOLON);
                }
                if (ccEmailAddrs.length() > 0) {
                    log.info("CC - " + ccEmailAddrs);
                }
            }
            log.info("SENT MAIL DETAILS - END");
        } catch (Exception e) {
            log.error("Exception in logSentMailDetails(EmailRqVM) - " + e);
        }

    }

}
