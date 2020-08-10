package mx.habil.mail.commons.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import lombok.extern.log4j.Log4j2;
import mx.habil.framework.support.HabilVo;

@Getter
@Setter
@Log4j2
public class AttachmentVo  extends HabilVo{
    
    private static final long serialVersionUID = 1L;

    @Tolerate
    public AttachmentVo(){
        log.debug("Creating AttachmentVo");
    }

    private String name;
    private byte[] content;


}