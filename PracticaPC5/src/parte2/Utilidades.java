package parte2;

public class Utilidades {
	
	public static boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
