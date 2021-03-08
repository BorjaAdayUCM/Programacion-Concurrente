package parte1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
		if(args.length < 1) {
			System.out.println("Necesario el numero de puerto del servidor.");
		}
		try {
			
			ServerSocket socket = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println("Servidor iniciado.\n");
			
			while(true) {
				System.out.println("Esperando conexiones.");
				Socket socketCliente = socket.accept();
				new Conexion(socketCliente).start();
			}
		}
		catch(Exception e) {
			System.out.println("Existe algun problema con el servidor.\nPuede que el puerto este usado o el servidor ya este iniciado.");
		}
	}

}
