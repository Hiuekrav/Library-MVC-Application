package pl.pas.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource(value = {
       "classpath:api.properties"
})
public class GeneralConfig {
}
