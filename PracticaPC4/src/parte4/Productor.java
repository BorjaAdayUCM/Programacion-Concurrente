package parte4;

public class Productor extends Thread {
	
	private Monitor monitor;
	private int MAX;
	
	public Productor(Monitor monitor, int MAX) {
		super();
		this.monitor = monitor;
		this.MAX = MAX;
	}
	
	public void run() {
		try {
			while(true) {
				int numAlmacenar = (int) (Math.random() * (this.MAX / 2)) + 1;
				Producto[] productos = new Producto[numAlmacenar];
				for(int i = 0; i < numAlmacenar; i++) productos[i] = new Producto();
				this.monitor.almacenar(productos);
				Thread.sleep(1000);
			}
		}
		catch(Exception e) {};
	}

}
