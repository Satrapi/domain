package com.artronics.sdwn.domain;

import com.artronics.sdwn.domain.config.PersistenceConfig;
import com.artronics.sdwn.domain.config.SdwnDomainConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SdwnDomainApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder.sources(SdwnDomainApplication.class,
						SdwnDomainConfig.class,
						PersistenceConfig.class)
			   .build().run(args);
	}
}
