package com.icoderman.config;


import com.icoderman.app.service.MyRemoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
@ComponentScan("com.icoderman.app")
public class AppConfig {

	@Bean
	public HessianProxyFactoryBean spitterService() {
		HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
		proxy.setServiceUrl("http://localhost:8080/myremote.service");
		proxy.setServiceInterface(MyRemoteService.class);
		return proxy;
	}

}
