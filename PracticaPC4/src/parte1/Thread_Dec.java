package parte1;

public class Thread_Dec extends Thread {
	
	private Monitor monitor;
	private int N;
	
	public Thread_Dec(Monitor monitor, int N) {
		super();
		this.N = N;
		this.monitor = monitor;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) this.monitor.decrementar();
	}
}
