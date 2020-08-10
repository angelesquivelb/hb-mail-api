package mx.habil.mail;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.util.HabilConstants;

@SpringBootTest
@Log4j2
class MailApplicationTests {

	@Test
	void contextLoads() {
		log.debug(HabilConstants.LOG_BEG);

		MailApplication.main( new String[] {} );
		
		assertTrue(true , "Context loaded in MailApplication");

		assertNotNull(MailApplication.class);

		log.debug(HabilConstants.LOG_END);
	}


}
