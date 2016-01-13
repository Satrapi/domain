package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.helpers.SeedNetworkGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(PersistenceConfig.class)
@PropertySource({
        "classpath:application-dev.properties",
//        "classpath:application-test.properties"
})
public class RepositoryConfigTest
{
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static SeedNetworkGraph getSeeder(){
        return new SeedNetworkGraph();
    }
}
