package mx.habil.mail.api;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.handler.HabilApiExceptionHandler;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.facade.MailFacade;

@Log4j2
@RestController
@RequestMapping("/api/mail")
public class MailController extends HabilApiExceptionHandler {

    @Autowired
    private MailFacade mailFacade;

    /**
     * Proposito: Send simple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 21/07/2020
     * @param mailVo Information to send email.
     * @return MailResponseBean Information of the email sent
     *         {@link MailResponseBean}
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/send")
    public ResponseEntity<Object> sendMailWithAttachment(@RequestBody MailVo mailVo) {
        log.debug(HabilConstants.LOG_BEG);
        
        ResponseEntity<Object> result = null;
        String serviceResponse = null;

            serviceResponse = mailFacade.sendMailWithAttachment(mailVo);

            result = new ResponseEntity<>(serviceResponse, HttpStatus.OK);
            
        log.debug(HabilConstants.LOG_END);
        return result;
    }

}