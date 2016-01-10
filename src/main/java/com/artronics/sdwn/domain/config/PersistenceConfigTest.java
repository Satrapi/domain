package com.artronics.sdwn.domain.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(PersistenceConfig.class)
@PropertySource("classpath:application-test.properties")
public class PersistenceConfigTest
{
    private final static Logger log = Logger.getLogger(PersistenceConfigTest.class);
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
