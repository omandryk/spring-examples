package app.beans;

import org.springframework.stereotype.Component;

@Component
public class PerformanceImpl implements Performance{

	public void perform() {
		System.out.println("Performing");
		throw new RuntimeException("Test Exception");
	}

	public void performSong(String song) {
		System.out.println("Performing song: " + song);
	}

}
