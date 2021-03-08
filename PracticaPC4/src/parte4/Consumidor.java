package parte4;

public class Consumidor extends Thread {

	private Monitor monitor;
	private int MAX;
	
	public Consumidor(Monitor monitor, int MAX) {
		super();
		this.monitor = monitor;
		this.MAX = MAX;
	}
	
	public void run() {
		try {
			while(true) {
				this.monitor.extraer((int) (Math.random() * (MAX / 2)) + 1);
				Thread.sleep(1000);
			}
		}
		catch(Exception e) {};
	}
}
