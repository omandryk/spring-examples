package com.icoderman.config;

import com.icoderman.app.service.MyRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletRegistration;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public HessianServiceExporter hessianExportedMyRemoteService(MyRemoteService myRemoteService) {
		HessianServiceExporter exporter = new HessianServiceExporter();
		exporter.setService(myRemoteService);
		exporter.setServiceInterface(MyRemoteService.class);
		return exporter;
	}

	@Bean
	public HandlerMapping hessianMapping() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.setProperty("/myremote.service", "hessianExportedMyRemoteService");
		mapping.setMappings(mappings);
		return mapping;
	}
}
