package com.artronics.sdwn.domain.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
//@Profile("dev")
//This line is added for sdwn referencing for sdwn-domain
@PropertySource("classpath:application-dev.properties")
public class SdwnDomainConfig
{
    private final static Logger log = Logger.getLogger(SdwnDomainConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
