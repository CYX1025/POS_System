package util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LocalDateTimeEx {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = now.format(formatter);
		
		System.out.println(formatted);

	}

}
