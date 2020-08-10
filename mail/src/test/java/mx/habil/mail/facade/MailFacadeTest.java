package mx.habil.mail.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.service.MailService;

@Log4j2
@ExtendWith(MockitoExtension.class)
public class MailFacadeTest {

    @InjectMocks
    private MailFacade mailFacade;

    @Mock
    private MailService mailService;

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Ok");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_fromNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setAlias("Notification");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_aliasNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_toNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_ccNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_bccNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_subjectNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);

        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setTo(new String[] { "alejandro.nares@habilgroup.com" });
        mailVo.setCc(new String[] { "alejandronares75@gmail.com" });
        mailVo.setBcc(new String[] { "alan.atenogenes@habilgroup.com" });
        mailVo.setBody("Esta es una prueba.");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");

        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

        String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);

        log.debug(HabilConstants.LOG_END);
    }

    /**
     * Proposito: Should send a aimple email.
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 22/07/2020
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @see {@link MailFacade#sendMail(MailVo)}
     */
    @Test
    @DisplayName("Should send a simple email")
    void sendMailTest_bodyNull() throws UnsupportedEncodingException, MessagingException {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailFacade);
        assertNotNull(mailService);
                                                               
        MailVo mailVo = new MailVo();

        mailVo.setFrom("email.notification@habil.mx");
        mailVo.setAlias("Notification");
        mailVo.setTo(new String[]{"alejandro.nares@habilgroup.com"});
        mailVo.setCc(new String[]{"alejandronares75@gmail.com"});
        mailVo.setBcc(new String[]{"alan.atenogenes@habilgroup.com"});
        mailVo.setSubject("Prueba a nivel Facade");
        mailVo.setIsFormatHtml("false");
        mailVo.setIsContentTemplate("true");


        when(mailService.sendMailWithAttachment(any(), any())).thenReturn("Error.");

		String messageResponse = mailFacade.sendMailWithAttachment(mailVo);

        log.debug("messageResponse: " + messageResponse);
        
        log.debug(HabilConstants.LOG_END);
    }

}