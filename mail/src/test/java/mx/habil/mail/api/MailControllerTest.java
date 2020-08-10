package mx.habil.mail.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.vo.MailVo;
import mx.habil.mail.facade.MailFacade;

@Log4j2
@WebMvcTest({MailController.class})
public class MailControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailFacade mailFacade;

    
    /**
	 * Proposito: Convierte un objeto JSON a String. 
	 * 
	 * @author 	alejandro.nares, Habil MX
	 * @version 1.0.0 - 22/07/2020
	 * @param   Object  - Object
	 *                   
	 * @return String - Contenido del objeto en formato de cadena (atributos y valores). 
	 * 
	 */
    public static String objectToJsonString(final Object object) {
        log.debug(HabilConstants.LOG_BEG);

        try {
            return new ObjectMapper().writeValueAsString(object);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Should send email. 
     *
     * @author  alejandro.nares, Hábil MX
     * @version 1.0.0, 22/07/2020
     * @throws Exception When an unexpected error occurs
     * @see {@link MailController#sendWithAttachment(MailContentBean)}
     */   
    @Test
    @DisplayName("Test para comprobar la funcionalidad del servicio de enviar un email simple")
    public void sendMailWithAttachment() {
        log.debug(HabilConstants.LOG_BEG);
        assertNotNull(mockMvc);

        MailVo mailVo = null;

        // SE ASIGAN LOS VALORES A LAS VARIABLES
        String from = "email.notification@habil.mx";
        String alias = "Notifications";
        String[] to = {"alejandro.nares@hbailgroup.com"};
        String[] cc = {"alejandronares75@gmail.com"};
        String[] bcc = {"alan.atenogenes@habilgroup.com"};
        String subject = "Prueba a nivel controller";
        String body = "Este email es una prueba.";
        String isHtmlText = "true";

        // SE INSTANCIA EL OBJETO mailVo, contactEntity y contactEntityAux
        mailVo = new MailVo();
        // SE SETEAN LOS DATOS AL OBJETO VO
        mailVo.setFrom(from);
        mailVo.setAlias(alias);
        mailVo.setTo(to);
        mailVo.setCc(cc);
        mailVo.setBcc(bcc);
        mailVo.setSubject(subject);
        mailVo.setBody(body);
        mailVo.setIsFormatHtml(isHtmlText);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Realizar la peticion
        try {

            doReturn("Ok").when(mailFacade).sendMailWithAttachment(mailVo);
            
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/mail/send")
                    .content(objectToJsonString(mailVo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception exception) {
            log.error("exception: " + exception.getMessage());
        }

        log.debug(HabilConstants.LOG_END);
    }

}