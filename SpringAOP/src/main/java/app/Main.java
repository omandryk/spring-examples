package app;

import app.beans.Encoreable;
import app.beans.Performance;
import config.SpringAOPConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringAOPConfig.class);
		Performance concert = context.getBean(Performance.class);
		concert.perform();
		concert.performSong("MySong");
		((Encoreable)concert).performEncore();
	}
}
