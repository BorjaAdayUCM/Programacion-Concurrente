package parte2;

public class Productor extends Thread {
	
	private Monitor monitor;
	
	public Productor(Monitor monitor) {
		super();
		this.monitor = monitor;
	}
	
	public void run() {
		try {
			while(true) {
				this.monitor.almacenar(new Producto());
				Thread.sleep(1000);
			}
		}
		catch(Exception e) {};
	}

}
