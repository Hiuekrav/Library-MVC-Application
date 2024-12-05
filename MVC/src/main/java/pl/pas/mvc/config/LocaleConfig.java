package pl.pas.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();

        // Ustawienie domyślnego języka (jeśli przeglądarka nie podaje języka)
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
}

