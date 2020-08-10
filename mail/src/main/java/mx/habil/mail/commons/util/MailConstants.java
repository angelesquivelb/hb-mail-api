package mx.habil.mail.commons.util;

public class MailConstants {

    private MailConstants(){
    }
    //General
    public static final String LOG_INICIO = "<====Inicio====>";
    public static final String LOG_FIN    = "<======Fin=====>";
    public static final String TIPE_TEXT_HTML = "html";
    public static final String TIPE_TEXT_PLAIN_TEXT    = "plainText";
    public static final Boolean VALUE_TRUE = Boolean.TRUE;
    public static final Boolean VALUE_FALSE = Boolean.FALSE;

    public static final String MENSAJE_ERROR = "Ocurrió un error inesperado al ";
    public static final String MENSAJE_NULL_EMPTY = " no debe ser nulo o estar vacío";
    
    public static final String MESSAGES_BASE_NAME = "i18n.messages";
    
    public static final Integer MAIL_SENDER_PORT = 587;
    public static final String MAIL_SENDER_HOST = "email-smtp.us-west-2.amazonaws.com";
    public static final String MAIL_SENDER_USER = "AKIAJXJRDE53JC4667GQ";
    public static final String MAIL_SENDER_PASS = "AqEld1sJ/PyfTgf0cvclLnDqhySRWjl6mA9Ksg44mjFt";
    
    public static final String MESSAGES_VALIDATION_EMPTY_NULL = "email.parameter.is.null.or.empty";
    public static final String MESSAGES_VALIDATION_EMAIL_INVALID = "email.parameter.email.invalid";
    public static final String MESSAGES_EXCEPTION_UNECPECTED_ERROR = "api.exception.unexpected.error";
    public static final String MESSAGES_EXCEPTION_INVALID_TYPE_TEXT = "api.exception.invalid.type.text";
    
}