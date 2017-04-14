package ecx.mpopijac.restaurants;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	Environment env;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        String absolutePath = StaticResourceConfiguration.class.getProtectionDomain().getCodeSource().getLocation().toString();
        absolutePath = absolutePath.replace("target/classes/", env.getProperty("upload.file.path"));
        absolutePath = absolutePath.replace("file:/","file:///");
        absolutePath +=File.separator;
        registry.addResourceHandler("/upload/**").addResourceLocations(absolutePath);
        registry.addResourceHandler("/admin/upload/**").addResourceLocations(absolutePath);
        registry.addResourceHandler("/user/upload/**").addResourceLocations(absolutePath);
    }
}