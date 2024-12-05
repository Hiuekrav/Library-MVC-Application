package pl.pas.mvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class InternationalizationConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        // Nazwa pliku bez rozszerzenia
        messageSource.setBasename("messages"); // Wskazuje na plik messages_pl.properties
        messageSource.setDefaultEncoding("UTF-8"); // Używaj kodowania UTF-8
        messageSource.setFallbackToSystemLocale(false); // Jeśli tłumaczenie nie istnieje, nie używaj systemowego języka
        return messageSource;
    }
}

