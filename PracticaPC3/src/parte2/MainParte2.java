//Borja Aday Guadalupe Luis
//Eduardo Bryan de Renovales Alvarado

package parte2;

import java.util.ArrayList;

public class MainParte2 {
	
	private static final int NP = 5;
	private static final int NC = 10;

	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		for(int i = 0; i < NP; i++) {
			hilos.add(new Productor(almacen));
		}
		
		for(int i = 0; i < NC; i++) {
			hilos.add(new Consumidor(almacen));
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