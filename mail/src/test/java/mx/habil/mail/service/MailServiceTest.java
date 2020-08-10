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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.AttachmentVo;
import mx.habil.mail.commons.vo.MailVo;

@Log4j2
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MailServiceTest {

    @Autowired
    private MailServiceImpl mailServiceImpl;


    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
    	);
        
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
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setListAttachmentVo(listAttachmentVo);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }
    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_tipeTextText() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String body = "Correo de prueba.";
        
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
        mailVo.setFormatType("plainText");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }
    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_formatTypeOtro() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
    	);
        
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
        mailVo.setFormatType("otro");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }


    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_template() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);
        
        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String template = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Hola {0}</h1>",
    	    "<p>te informamos que tu saldo hasta el momento es de {1}", 
    	    "<p><a href='https://www.habil.mx/'>Habil.</a>"
        );
        
        String[] params = {"Alejandro", "105.13"};
        
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
        mailVo.setTemplate(template);
        mailVo.setParams(params);
        mailVo.setListAttachmentVo(listAttachmentVo);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = true;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_exception() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@gmail.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandro.nares@gmail.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Este es un correo de prueba</h1>",
    	    "<p>Este email ha sido enviado gracias a un servicio de la empresa ", 
    	    "<a href='https://www.habil.mx/'>Habil.</a>"
    	);
        
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
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e.getMessage());
        }
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_ccNull() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String body = "Favor de no hacer caso.";
        
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
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setListAttachmentVo(listAttachmentVo);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_bccNull() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandronares75@gmail.com" };
        String subject = "Correo de prueba";
        String body = "Favor de no hacer caso.";
        
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
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setListAttachmentVo(listAttachmentVo);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_attacmenNull() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandronares75@gmail.com" };
        String bcc[] = { "alan.atenogenes@habilgroup.com" };
        String subject = "Correo de prueba";
        String body = "Favor de no hacer caso.";
        
        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }

    /**
     * 
     *
     * @author alejandro.nares, Habil MX
     * @version 1.0.0, 24/07/2020
     * @throws MessagingException
     * @throws IOException
     * @see {@link MailService#sendMailWithAttachment(String, String, String[], String[], String[], String, String, List)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo enviar correo con archivo adjuntoa nivel SERVICE")
    public void sendMailWithAttachment_attacmenEmpty() throws MessagingException, IOException {
        
		log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mailServiceImpl);

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notification";
        String to[] = { "alejandro.nares@habilgroup.com" };
        String cc[] = { "alejandronares75@gmail.com" };
        String subject = "Correo de prueba";
        String body = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Amazon SES SMTP Email Test</h1>",
    	    "<p>This email was sent with Amazon SES using the ", 
    	    "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
    	    " for <a href='https://www.java.com'>Java</a>."
        );
        
        List<AttachmentVo> listAttachmentVo = new ArrayList<AttachmentVo>();

        MailVo mailVo = new MailVo();
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setListAttachmentVo(listAttachmentVo);
        mailVo.setFormatType("html");
        
        Boolean isContentTemplate = false;

        try {
			String response = mailServiceImpl.sendMailWithAttachment(mailVo, isContentTemplate);

            log.debug(response);

        } catch (HabilServiceException e) {
            log.debug("exception  :: " + e);
        }
        
        log.debug(HabilConstants.LOG_END);
    }

}