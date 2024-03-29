package com.lendico.annuity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@PropertySource("classpath:api.properties")
@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

	@Value("${Service.Name}")
	private String serviceName;
	
	@Value("${Service.Type}")
	private String serviceType;

	@Value("${Service.Version}")
	private String serviceVersion;
	
	@Value("${Service.Terms}")
	private String termsOfService;
	
	@Value("${Service.ContactName}")
	private String contactName;
	
	@Value("${Service.ContactAddress}")
	private String contactAddress;
	
	@Value("${Service.ContactEmail}")
	private String contactEmail;
	
	@Value("${Service.License}")
	private String license;
	
	@Value("${Service.LicenseURL}")
	private String licenseURL;

	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())
	          .build().apiInfo(metaData())
					.tags(new Tag("Movie REST Controller", "Movie Search REST API controller"),
							new Tag("tag2", "Tag 2 description."),
							new Tag("tag2", "Tag 3 description."));
	    }

		private ApiInfo metaData() {
			
			return  new ApiInfo(serviceName, serviceType, serviceVersion, termsOfService,
					new Contact(contactName, contactAddress, contactEmail), license, licenseURL);
			
		}
}