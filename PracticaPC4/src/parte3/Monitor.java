package parte3;

public class Monitor{
	
	private Producto[] productos;
	private int nProductos;
	private int MAX;
	private int ini;
	private int fin;
	
	public Monitor() {
		super();
		this.ini = 0;
		this.fin = 0;
		this.nProductos = 0;
		this.MAX = MainParte3.MAX;
		this.productos = new Producto[this.MAX];
	}

	public synchronized void almacenar(Producto[] productos) throws InterruptedException {
		while(this.nProductos + productos.length > this.MAX) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para almacenar " + productos.length + " productos.");
			wait();
		}
		System.out.println("Se van a almacenar " + productos.length + " productos");
		for(int i = 0; i < productos.length; i++) {
			this.productos[this.fin] = productos[i];
			this.fin = (this.fin + 1) % this.MAX;
			this.nProductos++;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha almacenado el producto: " + productos[i] + ", Total: " + this.nProductos);
		}
		this.notifyAll();
	}

	public synchronized Producto[] extraer(int numExtraer) throws InterruptedException {
		while(numExtraer > this.nProductos) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para consumir " + numExtraer + " productos.");
			wait();
		}
		System.out.println("Se van a extraer " + numExtraer + " productos");
		Producto[] productosExtraidos = new Producto[numExtraer];
		for(int i = 0; i < numExtraer; i++) {
			productosExtraidos[i] = this.productos[ini];
			this.productos[ini] = null;
			this.ini = (this.ini + 1) % this.MAX;
			this.nProductos--;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha consumido el producto: " + productosExtraidos[i] + ", Total: " + this.nProductos);
		}
		this.notifyAll();
		return productosExtraidos;
	}

}
