package com.artronics.sdwn.domain;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.config.SdwnDomainConfig;
import com.artronics.sdwn.domain.repositories.NodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

//@SpringBootApplication
public class SdwnDomainApplication implements ApplicationListener<ContextRefreshedEvent>
{
	@Autowired
	private NodeRepo nodeRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{

	}

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder.sources(SdwnDomainApplication.class,
						SdwnDomainConfig.class,
						PersistenceConfig.class)
			   .build().run(args);
	}
}
