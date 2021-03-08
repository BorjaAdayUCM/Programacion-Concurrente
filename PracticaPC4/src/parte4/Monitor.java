package parte4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor{
	
	private Producto[] productos;
	private int nProductos;
	private int MAX;
	private int ini;
	private int fin;
	private Lock lock;
	private Condition noLleno;
	private Condition noVacio;
	
	public Monitor() {
		super();
		this.ini = 0;
		this.fin = 0;
		this.nProductos = 0;
		this.MAX = MainParte4.MAX;
		this.productos = new Producto[this.MAX];
		this.lock =  new ReentrantLock();
		this.noLleno = lock.newCondition();
		this.noVacio = lock.newCondition();
	}

	public void almacenar(Producto[] productos) throws InterruptedException {
		this.lock.lock();
		while(this.nProductos + productos.length > this.MAX) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para almacenar " + productos.length + " productos.");
			this.noLleno.await();
		}
		System.out.println("Se van a almacenar " + productos.length + " productos");
		for(int i = 0; i < productos.length; i++) {
			this.productos[this.fin] = productos[i];
			this.fin = (this.fin + 1) % this.MAX;
			this.nProductos++;
			System.out.println("El proceso " + Thread.currentThread().getId() + " ha almacenado el producto: " + productos[i] + ", Total: " + this.nProductos);
		}
		this.noVacio.signalAll();
		this.lock.unlock();
	}

	public Producto[] extraer(int numExtraer) throws InterruptedException {
		this.lock.lock();
		while(numExtraer > this.nProductos) {
			System.out.println("El proceso " + Thread.currentThread().getId() + " espera para consumir " + numExtraer + " productos.");
			this.noVacio.await();
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
		this.noLleno.signalAll();
		this.lock.unlock();
		return productosExtraidos;
	}

}
