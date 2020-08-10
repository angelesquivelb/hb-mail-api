package mx.habil.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import mx.habil.mail.commons.vo.MailVo;

public interface MailService {
    
    
    /**
     * Proposito: Send simple email.
     *
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0, 20/07/2020
     * @param   from    - The sender of the mail.
     * @param   isContentTemplate    - Flag indicating if it is plain text
     * @return  String Message from service
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String sendMailWithAttachment(MailVo mailVo, Boolean isContentTemplate);     
         
}