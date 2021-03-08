//Borja Aday Guadalupe Luis
//Eduardo Bryan de Renovales Alvarado

package parte1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;



public class MainParte1 {
	
	public static final int M = 500;
	public static final int N = 100;
	
	
	public static void main(String[] args) {
		
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		Semaphore sem = new Semaphore(1);
		VariableCompartida variableCompartida = new VariableCompartida();
		
		for(int i = 0; i < 2*M; i+=2) {
			hilos.add(new Thread_Inc(variableCompartida, sem, N));
			hilos.add(new Thread_Dec(variableCompartida, sem, N));
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
