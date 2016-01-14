package com.artronics.sdwn.domain.repositories;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.config.SeederConfig;
import com.artronics.sdwn.domain.entities.NetworkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({PersistenceConfig.class, SeederConfig.class})
@PropertySource({
        "classpath:application-dev.properties",
//        "classpath:application-test.properties"
})
public class RepositoryConfigTest
{
    @Autowired
    private SessionRepo sessionRepo;

//    @PostConstruct
//    @Transactional
//    public void initBean(){
//        seeder.seed(true);
//    }
//
//
//    @Bean
//    public SeedNetworkGraph getSeeder(){
//        return this.seeder;
//    }

    @Bean(name = "networkSession")
//    @DependsOn("sessionRepo")
//    @Transactional
    public NetworkSession getSession(){
//        seeder.seed(true);
//        return seeder.getActiveSession();
        NetworkSession session = new NetworkSession();
        return session;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
