package parte2;

public class VariableCompartida {
	
	private volatile int variableCompartida;
	
	public VariableCompartida() {
		this.variableCompartida = 0;
	}
	
	public int getVariableCompartida() {
		return variableCompartida;
	}

	public void incrementar() {
		this.variableCompartida++;
	}
	
	public void decrementar() {
		this.variableCompartida--;
	}

}
