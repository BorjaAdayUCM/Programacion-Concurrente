package parte2;

import java.util.ArrayList;

public class MainParte2 {
	
	public static final int M = 100;
	public static final int N = 1000;
	
	
	public static void main(String[] args) {
		
		VariableCompartida variableCompartida = new VariableCompartida();
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		
		for(int i = 0; i < N; i++) {
			hilos.add(new Thread_Inc(N, variableCompartida));
			hilos.add(new Thread_Dec(N, variableCompartida));
		}
		
		for(int i = 0;i < 2*N;i++) {
			hilos.get(i).start();
		}
		
		for(int i = 0;i < N;i++) {
			try {
				hilos.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(variableCompartida.getVariableCompartida());
		
	}
}
