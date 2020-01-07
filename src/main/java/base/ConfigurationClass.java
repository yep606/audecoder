package base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = "base")
public class ConfigurationClass {

    @Autowired
    Environment env;

    @Bean
    public BlankDisc disc(){

        return new BlankDisc(env.getProperty("disc.title"), env.getProperty("disc.author"));
    }

}
