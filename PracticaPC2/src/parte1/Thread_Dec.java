package parte1;

public class Thread_Dec extends Thread {
	
	private int N;
	private VariableCompartida variableCompartida;
	private LockCompartido lockCompartido;
	
	public Thread_Dec(int N, VariableCompartida variableCompartida, LockCompartido lockCompartido) {
		super();
		this.N = N;
		this.variableCompartida = variableCompartida;
		this.lockCompartido = lockCompartido;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) {
			this.lockCompartido.setIn1(true);
			this.lockCompartido.setLast(1);
			while(this.lockCompartido.isIn2() && this.lockCompartido.getLast() == 1);
			this.variableCompartida.decrementar();
			this.lockCompartido.setIn1(false);
		}
	}
}
