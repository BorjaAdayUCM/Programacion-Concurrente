package parte1;

public class Monitor {
	
	private int contador;
	
	public Monitor() {
		this.contador = 0;
	}
	
	public synchronized void incrementar() {
		this.contador++;
	}
	public synchronized void decrementar() {
		this.contador--;
	}
	
	public synchronized int getContador() {
		return this.contador;
	}
}
