package com.arun.singh.exception.manager.handler;

import com.arun.singh.exception.manager.base.ExceptionHandler;
import com.arun.singh.exception.manager.exception.BaseException;
import com.arun.singh.exception.manager.metadata.ExceptionMetaData;
import org.springframework.mail.MailSender;

public class ExceptionMailHandler implements ExceptionHandler {

    private MailSender mailSender;

    @Override
    public void handle(BaseException e,ExceptionMetaData exceptionMetaData) {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        mailSender.send(simpleMailMessage);
        System.out.println("ExceptionMailHandler.handle "+  String.format(exceptionMetaData.getMessage(),(Object[])e.getParams()));
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
