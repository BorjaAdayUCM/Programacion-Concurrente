package parte2;

import java.util.ArrayList;

public class MainParte2 {
	
	public static final int MAX = 6;
	private static final int NP = 3;
	private static final int NC = 3;

	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		for(int i = 0; i < NP; i++) {
			hilos.add(new Productor(monitor));
		}
		
		for(int i = 0; i < NC; i++) {
			hilos.add(new Consumidor(monitor));
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
