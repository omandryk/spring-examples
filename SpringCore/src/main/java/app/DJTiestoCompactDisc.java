package app;

import org.springframework.stereotype.Component;

@Component
public class DJTiestoCompactDisc implements CompactDisc {
	public static final String SINGER = "DJ Tiesto";
	public static final String TITLE = "Spring Song";


	public void play() {
		System.out.println("Playing: " + SINGER + " : " + TITLE);
	}
}
