package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.entities.NetworkSession;
import com.artronics.sdwn.domain.helpers.SeederRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({PersistenceConfig.class, SeederRunner.class})
@PropertySource({
        "classpath:application-dev.properties",
//        "classpath:application-test.properties"
})
public class RepositoryConfigTest
{
    @Autowired
    private SessionRepo sessionRepo;

    @Bean(name = "networkSession")
    public NetworkSession getSession(){
        NetworkSession session = new NetworkSession();
        return session;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
