package parte2;

public class Thread_Inc extends Thread {
	
	private int N;
	private VariableCompartida variableCompartida;
	
	public Thread_Inc(int N, VariableCompartida variableCompartida) {
		super();
		this.N = N;
		this.variableCompartida = variableCompartida;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) {
			this.variableCompartida.incrementar();
		}
	}
}