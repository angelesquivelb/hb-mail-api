package mx.habil.mail.commons.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import lombok.extern.log4j.Log4j2;
import mx.habil.framework.support.HabilVo;

@Getter
@Setter
@Log4j2
public class MailVo extends HabilVo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    @Tolerate
    public MailVo(){
        log.debug("Creando MailVo");
    }
    
    private String from;
    private String alias;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject; 
    private String body; 
    private String template; 
    private String[] params; 
    private String formatType;
    private String isFormatHtml; 
    private String isContentTemplate; 
    private List<AttachmentVo> listAttachmentVo;
}