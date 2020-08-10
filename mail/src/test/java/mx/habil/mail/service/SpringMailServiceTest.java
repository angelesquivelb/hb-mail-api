package mx.habil.mail.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.AttachmentVo;
import mx.habil.mail.commons.vo.MailVo;

@Log4j2
@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class SpringMailServiceTest {

    @Autowired
    private SpringMailServiceImpl springMailServiceImpl;


    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 03/08/2020
     * @see {@link SpringMailService#sendMail(MailVo, Boolean)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(springMailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = {};

        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
        );
        
        Boolean isFormatHtml = true;
        
        List<AttachmentVo> listAttachmentVo = new ArrayList<AttachmentVo>();

        AttachmentVo attachmentVo = new AttachmentVo(); 

        String fileName = "test/prueba.txt";
        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());


        byte[] array = Files.readAllBytes(file.toPath());
        attachmentVo.setName("prueba.txt");
        attachmentVo.setContent(array);

        listAttachmentVo.add(attachmentVo);

        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setListAttachmentVo(listAttachmentVo);

        springMailServiceImpl.sendMail(mailVo, isFormatHtml);
            
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 03/08/2020
     * @see {@link SpringMailService#sendMail(MailVo, Boolean)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_attachmenNull() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(springMailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = {};

        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
        );
        
        Boolean isFormatHtml = true;

        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);

        springMailServiceImpl.sendMail(mailVo, isFormatHtml);
            
        
        log.debug(HabilConstants.LOG_END);
    }
    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 03/08/2020
     * @see {@link SpringMailService#sendMail(MailVo, Boolean)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE recibiendo un error inesperado")
    public void sendMailWithAttachment_exceptionCatch() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(springMailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@gmail.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = {};

        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
        );
        
        Boolean isFormatHtml = true;
        

        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);

        try {
            
            springMailServiceImpl.sendMail(mailVo, isFormatHtml);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }
    

}