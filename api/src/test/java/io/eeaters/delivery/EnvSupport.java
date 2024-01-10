package io.eeaters.delivery;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

public interface EnvSupport {

    static ConfigurableEnvironment createEnv(String fileName) throws IOException {
        ConfigurableEnvironment configurableEnvironment = new StandardEnvironment();
        YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();
        String name = fileName;
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        EncodedResource encodedResource = new EncodedResource(classPathResource);
        List<PropertySource<?>> load = propertySourceLoader.load(name, encodedResource.getResource());

        for (PropertySource<?> propertySource : load) {
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }
        return configurableEnvironment;
    }

}
