package parte1;

public class Thread_Inc extends Thread {
	
	private int N;
	private VariableCompartida variableCompartida;
	private LockCompartido lockCompartido;
	
	public Thread_Inc(int N, VariableCompartida variableCompartida, LockCompartido lockCompartido) {
		super();
		this.N = N;
		this.variableCompartida = variableCompartida;
		this.lockCompartido = lockCompartido;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) {
			this.lockCompartido.setIn2(true);
			this.lockCompartido.setLast(2);
			while(this.lockCompartido.isIn1() && this.lockCompartido.getLast() == 2);
			this.variableCompartida.incrementar();
			this.lockCompartido.setIn2(false);
		}
	}
}