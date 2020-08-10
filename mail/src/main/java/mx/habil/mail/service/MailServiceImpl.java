package mx.habil.mail.service;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.util.MailConstants;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.helper.ValidatorHelper;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Log4j2
@Service("MailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private SpringMailService springMailService;

    @Override
    public String sendMailWithAttachment(MailVo mailVo, Boolean isContentTemplate) {        
                log.debug(HabilConstants.LOG_BEG);

                String result = null;
                String mensajeExcepcion = null;
                ResourceBundle resourceBundle = null;

                Object[] params = null;
                String template = null;
                String[] cc = null;
                String[] bcc = null;
                Boolean isFormatHtml = null;
        
                // Validations
                ValidatorHelper.validateStringAndThrowException(mailVo.getFrom(), "from", HttpStatus.CONFLICT);
                ValidatorHelper.validateStringAndThrowException(mailVo.getAlias(), "alias", HttpStatus.CONFLICT);
                ValidatorHelper.validateListObjectgNotNullAndThrowException(mailVo.getTo(), "to", HttpStatus.CONFLICT);
                ValidatorHelper.validateListEmailsAndThrowException(mailVo.getTo(), HttpStatus.CONFLICT);
                ValidatorHelper.validateListEmailsAndThrowException(mailVo.getCc(), HttpStatus.CONFLICT);
                ValidatorHelper.validateListEmailsAndThrowException(mailVo.getBcc(), HttpStatus.CONFLICT);
                ValidatorHelper.validateStringAndThrowException(mailVo.getSubject(), "subject", HttpStatus.CONFLICT);
                ValidatorHelper.validateObjectAndThrowException(isContentTemplate, "isContentTemplate", HttpStatus.CONFLICT);
                ValidatorHelper.validateStringAndThrowException(mailVo.getFormatType(), "formatType", HttpStatus.CONFLICT);
                

                if(MailConstants.TIPE_TEXT_HTML.equals(mailVo.getFormatType())){
            
                    isFormatHtml = MailConstants.VALUE_TRUE;
        
                }else if(MailConstants.TIPE_TEXT_PLAIN_TEXT.equals(mailVo.getFormatType())){
        
                    isFormatHtml = MailConstants.VALUE_FALSE;
                }else{
        
                    //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
                    resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
                    //SE OBTIENE EL MENSAJE DE EXCEPCION
                    mensajeExcepcion = resourceBundle.getString(MailConstants.MESSAGES_EXCEPTION_INVALID_TYPE_TEXT);
                    throw new HabilServiceException(mensajeExcepcion, HttpStatus.CONFLICT);
                }
                
                cc = this.isNullorEmptyArray(mailVo.getCc());
                mailVo.setCc(cc);
                
                bcc = this.isNullorEmptyArray(mailVo.getBcc());
                mailVo.setBcc(bcc);
                
                    
                if(MailConstants.VALUE_FALSE.equals(isContentTemplate)){
                        
                    ValidatorHelper.validateStringAndThrowException(mailVo.getBody(), "body", HttpStatus.CONFLICT);
                    
                }else{

                    ValidatorHelper.validateStringAndThrowException(mailVo.getTemplate(), "template", HttpStatus.CONFLICT);
                    ValidatorHelper.validateListObjectgNotNullAndThrowException(mailVo.getParams(), "params", HttpStatus.CONFLICT);
                    params = mailVo.getParams();
                    template = MessageFormat.format(mailVo.getTemplate(), params);
                    mailVo.setBody(template);
                    
                }

                springMailService.sendMail(mailVo, isFormatHtml);
                
                result = "Ok";
        
                log.debug(HabilConstants.LOG_END);
                return result;
    }

   
    private String[] isNullorEmptyArray(String[] arrayString) {
        
        String[] result = null;

        if (ArrayUtils.isEmpty(arrayString)) {
            result = new String[] {};
        }else{
            result = arrayString;
        }

        return result;
    }

}