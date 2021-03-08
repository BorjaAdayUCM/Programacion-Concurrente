package parte2;

public class Thread_Inc extends Thread {
	
	private VariableCompartida variableCompartida;
	private Lock lock;
	private int id;
	private int N;
	
	public Thread_Inc(VariableCompartida variableCompartida, Lock lock, int id, int N) {
		super();
		this.variableCompartida = variableCompartida;
		this.lock = lock;
		this.id = id;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) {
			lock.takeLock(this.id);
			this.variableCompartida.incrementar();
			lock.releaseLock(this.id);
		}
	}
}
