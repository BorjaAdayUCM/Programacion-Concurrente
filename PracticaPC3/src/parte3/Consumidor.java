package parte3;

public class Consumidor extends Thread {
	
	private Almacen almacen;
	
	public Consumidor(Almacen almacen) {
		super();
		this.almacen = almacen;
	}
	
	public void run() {
		try {
			while(true) {
				this.almacen.extraer();
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {}
	}
}
