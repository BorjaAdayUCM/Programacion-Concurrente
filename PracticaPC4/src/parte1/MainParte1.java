package parte1;

import java.util.ArrayList;

public class MainParte1 {
	
	public static final int N = 500;
	public static final int M = 100;
	
	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		for(int i = 0; i < M; i++) {
			hilos.add(new Thread_Inc(monitor, N));
			hilos.add(new Thread_Dec(monitor, N));
		}
		
		for(int i = 0; i < M * 2; i++) {
			hilos.get(i).start();;
		}
		
		for(int i = 0; i < M * 2; i++) {
			try {
				hilos.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(monitor.getContador());
	}
}
