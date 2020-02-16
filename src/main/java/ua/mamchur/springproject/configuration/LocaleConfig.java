package ua.mamchur.springproject.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Locale;
import java.util.function.Function;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

    @Bean
    public Function<String, String> changeLanguage() {
        return lang -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(lang).toUriString();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }

}
