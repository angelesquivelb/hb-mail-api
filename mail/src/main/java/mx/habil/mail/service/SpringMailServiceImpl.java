package mx.habil.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.mail.commons.util.MailConstants;
import mx.habil.mail.commons.vo.AttachmentVo;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.helper.ValidatorHelper;

import java.util.ResourceBundle;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Log4j2
@Service
public class SpringMailServiceImpl implements SpringMailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Async
	public void sendMail(MailVo mailVo, Boolean isFormatHtml) {
        
        String mensajeExcepcion = null;
        ResourceBundle resourceBundle = null;

        try{

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    
            mimeMessageHelper.setFrom(new InternetAddress(mailVo.getFrom(), mailVo.getAlias()));
            mimeMessageHelper.setTo(mailVo.getTo());
            
            mimeMessageHelper.setCc(mailVo.getCc());
            
            mimeMessageHelper.setBcc(mailVo.getBcc());
            
            mimeMessageHelper.setSubject(mailVo.getSubject());

                
            ValidatorHelper.validateStringAndThrowException(mailVo.getBody(), "body", HttpStatus.CONFLICT);
            mimeMessageHelper.setText(mailVo.getBody(), isFormatHtml);

            if(mailVo.getListAttachmentVo()!=null){
                log.debug("Have attachments");
                for(AttachmentVo attachmentVo : mailVo.getListAttachmentVo()){
                    mimeMessageHelper.addAttachment(attachmentVo.getName(),new ByteArrayResource(attachmentVo.getContent()));
                }
            }
    
            log.debug("Sending...");
    
            mailSender.send(mimeMessage);
    
            log.debug("Email sent!");

        }catch (Exception e) {
            log.error("Error message: " + e.getMessage());
            //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
            resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = resourceBundle.getString(MailConstants.MESSAGES_EXCEPTION_UNECPECTED_ERROR);

            throw new HabilServiceException(mensajeExcepcion, HttpStatus.CONFLICT);
        }

	}

}