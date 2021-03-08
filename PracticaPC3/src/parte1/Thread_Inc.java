package parte1;

import java.util.concurrent.Semaphore;

public class Thread_Inc extends Thread {
	
	private int N;
	private Semaphore sem;
	private VariableCompartida variableCompartida;
	
	public Thread_Inc(VariableCompartida variableCompartida, Semaphore sem, int N) {
		super();
		this.N = N;
		this.sem = sem;
		this.variableCompartida = variableCompartida;
	}
	
	public void run() {
		try {
			for(int i = 0; i < this.N; i++) {
				sem.acquire();
				this.variableCompartida.incrementar();
				sem.release();
			}
			
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
