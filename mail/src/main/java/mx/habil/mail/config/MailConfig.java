package mx.habil.mail.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import lombok.extern.log4j.Log4j2;
import mx.habil.framework.helper.HttpHelper;
import mx.habil.framework.util.HabilConstants;
import mx.habil.mail.commons.util.MailConstants;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Log4j2
public class MailConfig implements WebMvcConfigurer {

    @Value("${mail.host}") private String host;
    @Value("${mail.user}") private String username;
    @Value("${mail.pass}") private String pass;
    @Value("${mail.port}") private String port;
    

    @Bean
    public ObjectMapper objectMapper() {
        log.debug(HabilConstants.LOG_BEG);
        log.debug(HabilConstants.LOG_END);
        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).findAndRegisterModules();
    }

    @Bean
    public HttpHelper httpHelper(ObjectMapper objectMapper) {
        log.debug(HabilConstants.LOG_BEG);
        log.debug(HabilConstants.LOG_END);
        return new HttpHelper(objectMapper);
    }

    @Bean
    public LocaleResolver localResolver() {
        log.debug(HabilConstants.LOG_BEG);

        Locale locale = new Locale.Builder().setLanguage("es").build();
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(locale);

        log.debug(HabilConstants.LOG_END);
        return acceptHeaderLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.debug(HabilConstants.LOG_BEG);

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);

        log.debug(HabilConstants.LOG_BEG);
    }

    @Bean
    public MessageSource messageSource() {
        log.debug(HabilConstants.LOG_BEG);

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        log.debug(HabilConstants.LOG_END);
        return messageSource;
    }

    
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        log.debug(HabilConstants.LOG_BEG);

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);

        log.debug(HabilConstants.LOG_END);
        return localValidatorFactoryBean;
    }
    
    // Swagger
    @Bean
    public Docket apiDocket() {
        log.debug(HabilConstants.LOG_BEG);
        log.debug(HabilConstants.LOG_END);
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("mx.habil.mail.api")).paths(PathSelectors.any()).build();
    }

    //JavaMailSender
    @Bean
    public JavaMailSender getJavaMailSender() {
        log.debug(HabilConstants.LOG_BEG);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MailConstants.MAIL_SENDER_HOST);
        mailSender.setPort(MailConstants.MAIL_SENDER_PORT);
        //mailSender.setHost(host);
        //mailSender.setPort(Integer.parseInt(port));
        
        mailSender.setUsername(MailConstants.MAIL_SENDER_USER);
        mailSender.setPassword(MailConstants.MAIL_SENDER_PASS);
        //mailSender.setUsername(username);
        //mailSender.setPassword(pass);
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
          
        log.debug(HabilConstants.LOG_END);
        return mailSender;
    }
        
}