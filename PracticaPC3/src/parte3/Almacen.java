package parte3;

import java.util.concurrent.Semaphore;

public class Almacen {

	private Producto productos[];
	private Semaphore vacio;
	private Semaphore lleno;
	private Semaphore mutexProd;
	private Semaphore mutexCons;
	private int ini;
	private int fin;
	
	public Almacen() {
		super();
		this.productos = new Producto[MainParte3.MAX];
		this.lleno = new Semaphore(0);
		this.vacio = new Semaphore(MainParte3.MAX);
		this.mutexProd = new Semaphore(1);
		this.mutexCons = new Semaphore(1);
		this.ini = 0;
		this.fin = 0;
	}
	
	public void almacenar(Producto producto)
	{
		try {
			this.vacio.acquire();
			this.mutexProd.acquire();
			this.productos[this.fin] = producto;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha almacenado el producto: " + this.productos[this.fin]);
			this.fin = (this.fin + 1) % MainParte3.MAX;
			this.mutexProd.release();
			this.lleno.release();
		} 
		catch (Exception e) {}
	}
	
	public Producto extraer()
	{
		Producto extraido = null;
		try {
			this.lleno.acquire();
			this.mutexCons.acquire();
			extraido = this.productos[ini];
			this.productos[ini] = null;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha consumido el producto: " + extraido);
			this.ini = (this.ini + 1) % MainParte3.MAX;
			this.mutexCons.release();
			this.vacio.release();
		} 
		catch (Exception e) {}
		return extraido;
	}
}
