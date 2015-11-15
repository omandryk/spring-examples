package com.icoderman.config;


import com.icoderman.app.service.MyRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan("com.icoderman.app")
public class AppConfig {

	@Bean
	public RmiServiceExporter rmiExporter(MyRemoteService myRemoteService) {
		RmiServiceExporter rmiExporter = new RmiServiceExporter();
		rmiExporter.setService(myRemoteService);
		rmiExporter.setServiceName("MyRemoteService");
		rmiExporter.setServiceInterface(MyRemoteService.class);
		return rmiExporter;
	}

}
