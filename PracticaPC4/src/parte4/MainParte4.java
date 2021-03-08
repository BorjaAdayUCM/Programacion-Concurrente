package parte4;

import java.util.ArrayList;

public class MainParte4 {
	
	protected static final int MAX = 20;
	private static final int NP = 6;
	private static final int NC = 6;

	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		for(int i = 0; i < NP; i++) {
			hilos.add(new Productor(monitor, MAX));
		}
		
		for(int i = 0; i < NC; i++) {
			hilos.add(new Consumidor(monitor, MAX));
		}
		
		for(int i = 0; i < NP + NC; i++) {
			hilos.get(i).start();
		}
		
		//No hace falta porque están en continuo funcionamiento.
		/*for(int i = 0; i < NP + NC; i++) {
			try {
				hilos.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/

	}
}
