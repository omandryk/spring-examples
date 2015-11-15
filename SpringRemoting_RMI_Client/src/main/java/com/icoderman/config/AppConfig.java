package com.icoderman.config;


import com.icoderman.app.service.MyRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
@ComponentScan("com.icoderman.app")
public class AppConfig {

	@Bean
	public RmiProxyFactoryBean myRemoteService() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl("rmi://localhost/MyRemoteService");
		rmiProxy.setServiceInterface(MyRemoteService.class);
		return rmiProxy;
	}

}
