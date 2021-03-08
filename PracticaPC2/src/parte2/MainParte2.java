//Borja Aday Guadalupe Luis
//Eduardo Bryan de Renovales Alvarado

package parte2;

import java.util.ArrayList;

public class MainParte2 {

	public static final int M = 100;
	public static final int N = 30;
	
	
	public static void main(String[] args) {
		
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		Lock lock;
		
		//lock = new LockRompeEmpate(2*M);
		lock = new LockTicket();
		//lock = new LockBakery(2*M);
		
		VariableCompartida variableCompartida = new VariableCompartida();
		
		for(int i = 0; i < 2*M; i+=2) {
			hilos.add(new Thread_Inc(variableCompartida, lock, i, N));
			hilos.add(new Thread_Dec(variableCompartida, lock, i + 1, N));
		}
		
		for(int i = 0; i < 2*M; i++) {
			hilos.get(i).start();
		}
		
		for(int i = 0; i < 2*M; i++) {
			try {
				hilos.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(variableCompartida.getVariableCompartida());
	
	}
}