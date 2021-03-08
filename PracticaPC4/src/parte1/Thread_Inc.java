package parte1;

public class Thread_Inc extends Thread {
	
	private Monitor monitor;
	private int N;

	
	public Thread_Inc(Monitor monitor, int N) {
		super();
		this.monitor = monitor;
		this.N = N;
	}
	
	public void run() {
		for(int i = 0; i < this.N; i++) this.monitor.incrementar();
	}
}
