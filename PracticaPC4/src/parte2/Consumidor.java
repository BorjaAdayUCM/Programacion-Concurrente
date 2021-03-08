package parte2;

public class Consumidor extends Thread {

	private Monitor monitor;
	
	public Consumidor(Monitor monitor) {
		super();
		this.monitor = monitor;
	}
	
	public void run() {
		try {
			while(true) {
				this.monitor.extraer();
				Thread.sleep(1000);
			}
		}
		catch(Exception e) {};
	}
}
