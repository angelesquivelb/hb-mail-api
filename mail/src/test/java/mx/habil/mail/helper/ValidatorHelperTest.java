package mx.habil.mail.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.exception.HabilServiceException;
import mx.habil.mail.commons.util.MailConstants;
import mx.habil.mail.commons.vo.MailVo;

@Log4j2
public class ValidatorHelperTest {

	
    /**
     * Should send a simple email.
     *
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0, 23/07/2020
     * @see {@link ValidatorHelper.isNullObject(Object)
     */
    @Test
    @DisplayName("Test para validar la funcionalidad del metodo validador de objetos vo")
    public void isNullObjectTest() {
        log.debug(MailConstants.LOG_INICIO);

        assertNotNull(ValidatorHelper.isNullObject(new MailVo()));

        MailVo mailVo = new MailVo();

        Boolean resultado  = ValidatorHelper.isNullObject(mailVo);

        log.debug("resultado: "+resultado);

        log.debug(MailConstants.LOG_FIN);

    }


    @Test
    @DisplayName("Test para validar la funcionalidad del metodo que valida string y lanza la excepcion")
    public void validateStringAndThrowExceptionTest() {
        log.debug(MailConstants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String fieldValue = "";
        String fieldName = "name";

        try{

            ValidatorHelper.validateStringAndThrowException(fieldValue, fieldName, httpStatus);

        }catch(HabilServiceException habilServiceException){
            log.error(habilServiceException.getMessage());
        }

        log.debug(MailConstants.LOG_FIN);

    }



    @Test
    @DisplayName("Test para validar la funcionalidad del metodo que valida objetos boolean y lanza la excepcion en caso de ser nulos")
    public void validateObjectAndThrowExceptionTest() {
        log.debug(MailConstants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        MailVo mailVo = null;
        String fieldName = "mailVo";
        try{

            ValidatorHelper.validateObjectAndThrowException(mailVo, fieldName, httpStatus);

        }catch(HabilServiceException habilServiceException){
            log.error(habilServiceException.getMessage());
        }
        log.debug(MailConstants.LOG_FIN);

    }

    @Test
    @DisplayName("Test para validar la funcionalidad del metodo que valida una lista de correos y lanza la excepcion en caso de ser nulos")
    public void validateListEmailsNotNullAndThrowExceptionTest() {
        log.debug(MailConstants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String[] listEmail = null;
        String name = "to";

        try{

            ValidatorHelper.validateListObjectgNotNullAndThrowException(listEmail, name, httpStatus);

        }catch(HabilServiceException habilServiceException){
            log.error(habilServiceException.getMessage());
        }
        log.debug(MailConstants.LOG_FIN);

    }

    @Test
    @DisplayName("Test para validar la funcionalidad del metodo que valida el formato de una lista de correos y lanza la excepcion en caso de no contar con el formato correcto")
    public void validateListEmailsAndThrowExceptionTest() {
        log.debug(MailConstants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String[] listEmail = {"alejandro.nares@habil"};

        try{

            ValidatorHelper.validateListEmailsAndThrowException(listEmail, httpStatus);

        }catch(HabilServiceException habilServiceException){
            log.error(habilServiceException.getMessage());
        }
        log.debug(MailConstants.LOG_FIN);

    }

    @Test
    @DisplayName("Test para validar la funcionalidad del metodo que valida el formato de una lista de correos en caso de ser nula")
    public void validateListEmailsAndThrowExceptionTest_listaNull() {
        log.debug(MailConstants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String[] listEmail = null;

        try{

            ValidatorHelper.validateListEmailsAndThrowException(listEmail, httpStatus);

        }catch(HabilServiceException habilServiceException){
            log.error(habilServiceException.getMessage());
        }
        log.debug(MailConstants.LOG_FIN);

    }

}