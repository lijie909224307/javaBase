import java.util.Calendar;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
		Date bigTime = calendar.getTime();
		System.out.println(bigTime);
		
		calendar.add(Calendar.SECOND, 1);
		Date smallTime = calendar.getTime();
		System.out.println(smallTime);
		
	}
}
