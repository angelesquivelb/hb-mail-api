package mx.habil.mail.helper;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.mail.commons.util.MailConstants;

@Log4j2
public class ValidatorHelper {  
    
     
    private ValidatorHelper() {
    }


    /**
     * Proposito: Validar si el objeto es nulo
     * 
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0 - 22/07/2020
     * @param object - Objeto a evaluar
     * 
     * @return Boolean - Estatus del entity
     */
    public static Boolean isNullObject(Object object) {
        
        return object == null;
    }
    
    
    /**
     * Proposito: Validar si el objeto es nulo
     * 
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0 - 22/07/2020
     * @param object - Objeto a evaluar
     * 
     * @return Boolean - Estatus del entity
     */
    public static void validateObjectAndThrowException(Object param, String name, HttpStatus httpStatus){
        log.debug(MailConstants.LOG_INICIO);
        String mensajeExcepcion = null;
        ResourceBundle resourceBundle = null;

        if(MailConstants.VALUE_TRUE.equals(ValidatorHelper.isNullObject(param))){
            
            //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
            resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = MessageFormat.format(resourceBundle.getString(MailConstants.MESSAGES_VALIDATION_EMPTY_NULL),name);
 
            throw new HabilServiceException(mensajeExcepcion, new NullPointerException(mensajeExcepcion),httpStatus);

        }
        
        log.debug(MailConstants.LOG_FIN);
    }

    
    /**
     * Proposito : Validar si el campo de tipo string es nulo o vacio y si lo es lanzar excepcion
     * 
     * @author  alejandro.nares, Habil MX
	 * @version 1.0.0 - 23/07/2020
     * 
     * @param value - Valor del campo a evaluar
     * @param fieldName - Nombre del campo evaluado
     * @param httpStatus - Estatus de la petición realizada
     *
     */
    public static void validateStringAndThrowException(String value, String fieldName, HttpStatus httpStatus){
        log.debug(MailConstants.LOG_INICIO);
        String mensajeExcepcion = null;
        ResourceBundle resourceBundle = null;
        
        if(GenericValidator.isBlankOrNull(value)){
            //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
            resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = MessageFormat.format(resourceBundle.getString(MailConstants.MESSAGES_VALIDATION_EMPTY_NULL), fieldName);

            throw new HabilServiceException(mensajeExcepcion, new NullPointerException(mensajeExcepcion),httpStatus);
        }
        log.debug(MailConstants.LOG_FIN);
    }
    
    public static void validateListObjectgNotNullAndThrowException(Object[] listEmail, String name, HttpStatus httpStatus){
        log.debug(MailConstants.LOG_INICIO);
        String mensajeExcepcion = null;
        ResourceBundle resourceBundle = null;

        if(ArrayUtils.isEmpty(listEmail)){
            
            //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
            resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = MessageFormat.format(resourceBundle.getString(MailConstants.MESSAGES_VALIDATION_EMPTY_NULL),name);
 
            throw new HabilServiceException(mensajeExcepcion, new NullPointerException(mensajeExcepcion),httpStatus);

        }
        
        log.debug(MailConstants.LOG_FIN);
    }
    
    public static void validateListEmailsAndThrowException(String[] listEmail, HttpStatus httpStatus){
        log.debug(MailConstants.LOG_INICIO);
        String mensajeExcepcion = null;
        ResourceBundle resourceBundle = null;

        if(listEmail != null){
            for(String email : listEmail){

                if(!GenericValidator.isEmail(email)){
                    
                    //SE ASIGNA LA RUTA DONDE SE BUSCARA EL MENSAJE Y EL LENGUAJE
                    resourceBundle  = ResourceBundle.getBundle(MailConstants.MESSAGES_BASE_NAME,LocaleContextHolder.getLocale());
                    //SE OBTIENE EL MENSAJE DE EXCEPCION
                    mensajeExcepcion = MessageFormat.format(resourceBundle.getString(MailConstants.MESSAGES_VALIDATION_EMAIL_INVALID),email);

                    throw new HabilServiceException(mensajeExcepcion, new NullPointerException(mensajeExcepcion),httpStatus);
                    
                }

            }
        }
        
        log.debug(MailConstants.LOG_FIN);
    }
}