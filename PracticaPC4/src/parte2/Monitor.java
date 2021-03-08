 package parte2;

public class Monitor {
	
	private Producto[] productos;
	private int nProductos;
	private int MAX;
	private int ini;
	private int fin;
	
	public Monitor() {
		super();
		this.ini = 0; //pos consumir
		this.fin = 0; //pos producir
		this.nProductos = 0;
		this.MAX = MainParte2.MAX;
		this.productos = new Producto[this.MAX];
	}

	public synchronized void almacenar(Producto producto) throws InterruptedException {
		while(this.nProductos == this.MAX) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para almacenar");
			wait();
		}
		this.productos[this.fin] = producto;
		this.fin = (this.fin + 1) % this.MAX;
		this.nProductos++;
		System.out.println("El proceso " + Thread.currentThread().getId() + " ha almacenado el producto: " + producto + ", Total: " + this.nProductos);
		this.notifyAll();
	}

	public synchronized Producto extraer() throws InterruptedException {
		while(this.nProductos == 0) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para extraer");
			wait();
		}
		Producto prodExtraido = this.productos[ini];
		this.productos[ini] = null;
		this.ini = (this.ini + 1) % this.MAX;
		this.nProductos--;
		System.out.println("El proceso " + Thread.currentThread().getId() + " ha consumido el producto: " + prodExtraido + ", Total: " + this.nProductos);
		this.notifyAll();
		return prodExtraido;
	}

}
