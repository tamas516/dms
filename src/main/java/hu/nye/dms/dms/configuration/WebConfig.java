package hu.nye.dms.dms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler(
                    "/webjars/**",
                    "/img/**",
                    "/css/**",
                    "/js/**")
            .addResourceLocations(
                    "classpath:/META-INF/resources/webjars/",
                    "classpath:/static/img/",
                    "classpath:/static/css/",
                    "classpath:/static/js/");
  }

}
