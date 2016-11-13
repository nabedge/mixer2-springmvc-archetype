#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import org.mixer2.Mixer2Engine;
import org.mixer2.spring.webmvc.Mixer2XhtmlViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public Mixer2Engine mixer2Engine() {
        return Mixer2EngineSingleton.getInstance();
    }

    @Bean
    public Mixer2XhtmlViewResolver mixer2XhtmlViewResolver() {
        Mixer2XhtmlViewResolver resolver = new Mixer2XhtmlViewResolver();
        resolver.setPrefix("classpath:/m2template/");
        resolver.setSuffix(".html");
        resolver.setBasePackage("${package}.view");
        resolver.setMixer2Engine(mixer2Engine());
        resolver.setOrder(100);
        return resolver;
    }
}