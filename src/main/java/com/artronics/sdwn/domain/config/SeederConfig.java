package com.artronics.sdwn.domain.config;

import com.artronics.sdwn.domain.helpers.SeedNetworkGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ComponentScan(basePackages = "com.artronics.sdwn.domain.helpers")
public class SeederConfig implements ApplicationListener<ContextRefreshedEvent>
{
        @Autowired
    private SeedNetworkGraph seeder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        seeder.seed(true);
    }
//
//    @PostConstruct
//    @Transactional
//    public void initBean(){
//
//        seeder.seed(true);
//    }


}
