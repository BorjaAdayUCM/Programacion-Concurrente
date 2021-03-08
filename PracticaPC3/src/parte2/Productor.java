package parte2;

public class Productor extends Thread {
	
	private Almacen almacen;
	
	public Productor(Almacen almacen) {
		super();
		this.almacen = almacen;
	}
	
	public void run() {
		try {
			while(true) {
				this.almacen.almacenar(new Producto());
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {}
	}
}
