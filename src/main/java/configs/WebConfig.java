package configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map URLs starting with "/photos/**" to the file system location "file:photo/"
        registry.addResourceHandler("/photos/**")
                .addResourceLocations("file:photos/", "file:photo/");
    }
}
