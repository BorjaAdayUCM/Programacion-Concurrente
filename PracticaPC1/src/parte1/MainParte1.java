package parte1;

import java.util.ArrayList;

public class MainParte1 {
	
	private static final int MAX = 4;
	private static final int SEC = 1;
	
	public static void main(String[] args) {
		
		ArrayList<Thread_Time> listThreads = new ArrayList<Thread_Time>();
	
		for(int i = 0; i < MAX; i++) {
			listThreads.add(new Thread_Time(i, 1000 * SEC));
		}
		
		for(int i = 0; i < MAX; i++) {
			listThreads.get(i).start();
		}
		
		for(int i = 0; i < MAX; i++) {
			try {
				listThreads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("He terminado");
	}
}
