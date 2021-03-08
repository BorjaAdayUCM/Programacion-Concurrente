package parte2;

import java.util.concurrent.Semaphore;

public class Almacen {

	private Producto producto;
	private Semaphore vacio;
	private Semaphore lleno;
	
	public Almacen() {
		super();
		this.producto = null;
		this.lleno = new Semaphore(0);
		this.vacio = new Semaphore(1);
	}
	
	public void almacenar(Producto producto) {
		try {
			this.vacio.acquire();
			this.producto = producto;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha almacenado el producto: " + this.producto);
			this.lleno.release();
		} 
		catch (InterruptedException e) {}
	}
	
	public Producto extraer() {
		Producto extraido = null;
		try {
			this.lleno.acquire();
			extraido = this.producto;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha consumido el producto: " + this.producto);
			this.producto = null;
			this.vacio.release();
		}
		catch (InterruptedException e) {}
		return extraido ;
	}
}
