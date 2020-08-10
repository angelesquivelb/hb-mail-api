package mx.habil.mail.facade;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.helper.ValidatorHelper;
import mx.habil.mail.service.MailService;

@Log4j2
@Service
public class MailFacade {

    @Autowired
    private MailService mailService;

    /**
     * Proposito: Send simple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 21/07/2020
     * @param mailVo Information to send email
     * @return String Response of the service
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */

    public String sendMailWithAttachment(MailVo mailVo) {
        log.debug(HabilConstants.LOG_BEG);

        String result = null; 

        Boolean isContentTemplate = false;

        //VALIDACIONES
        ValidatorHelper.validateObjectAndThrowException(mailVo, "mailVo", HttpStatus.CONFLICT);
        ValidatorHelper.validateStringAndThrowException(mailVo.getIsContentTemplate(), "isContentTemplate", HttpStatus.CONFLICT);

        
        isContentTemplate = Boolean.parseBoolean(mailVo.getIsContentTemplate());

		result = mailService.sendMailWithAttachment(mailVo, isContentTemplate);

        log.debug(HabilConstants.LOG_END);
        return result;
    }
    
}