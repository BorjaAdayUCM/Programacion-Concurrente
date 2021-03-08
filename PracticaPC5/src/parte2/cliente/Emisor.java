package parte2.cliente;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import parte2.mensajes.Mensaje_Preparado_ClienteServidor;

public class Emisor extends Thread {
	
	private int puerto;
	private String fichero;
	private Semaphore semaphoreEmisor;
	private Mensaje_Preparado_ClienteServidor mensaje;
	
	public Emisor(int puerto, String fichero, Semaphore semaphoreEmisor, Mensaje_Preparado_ClienteServidor mensaje) throws IOException {
		this.puerto = puerto;
		this.fichero = fichero;
		this.semaphoreEmisor = semaphoreEmisor;
		this.mensaje = mensaje;
	}
	
	public void run() {
		try {
			this.semaphoreEmisor.acquire();
			OyenteServidor.getCliente().enviarPreparadoClienteServidor(this.mensaje);
			ServerSocket socketServidor = new ServerSocket(this.puerto);
			Socket socketCliente = socketServidor.accept();
				
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(this.fichero));
			OutputStream fout = socketCliente.getOutputStream();
				
			byte[] bytes = new byte[16*1024];
			int count = -1;
			while((count = fin.read(bytes)) > 0) fout.write(bytes, 0, count);
				
			fout.close();
			fin.close();
			socketCliente.close();
			socketServidor.close();
			/*WAIT_TIME*/ Thread.sleep(1000); /*WAIT_TIME*/
			this.semaphoreEmisor.release();
			
		} catch (IOException | InterruptedException e) {
			this.semaphoreEmisor.release();
		}
		
	}
}
