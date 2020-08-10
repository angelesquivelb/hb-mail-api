package mx.habil.mail.service;

import mx.habil.mail.commons.vo.MailVo;

public interface SpringMailService {
    
    
    /**
     * Proposito: Send email.
     *
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0, 31/07/2020
     * @param   mailVo    - Informacion que se va a mandar en el correo.
     * @param   isFormatHtml    - Bandera que indica si el formato que se enviara sera en html.
     */
    public void sendMail(MailVo mailVo, Boolean isFormatHtml);   
         
}