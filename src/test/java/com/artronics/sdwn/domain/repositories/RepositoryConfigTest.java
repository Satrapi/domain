package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.helpers.SeedNetworkGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@Import(PersistenceConfig.class)
@PropertySource({
        "classpath:application-dev.properties",
//        "classpath:application-test.properties"
})
public class RepositoryConfigTest
{
    private SeedNetworkGraph seeder;

    @PostConstruct
    public void initBean(){
        this.seeder = new SeedNetworkGraph();
    }


    @Bean
    public SeedNetworkGraph getSeeder(){
        return this.seeder;
    }

    @Bean
    public NetworkSession getSession(){
        return seeder.getActiveSession();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
