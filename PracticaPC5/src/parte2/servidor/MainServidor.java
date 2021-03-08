package parte2.servidor;

import java.io.IOException;

public class MainServidor {

	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Debe introducir la ip y el puerto del servidor.");
			return;
		}
		
		try {
			new Servidor(args[0], Integer.parseInt(args[1])).start();
		} catch (NumberFormatException | IOException e) {
			if(e instanceof NumberFormatException) System.out.println("El puerto debe ser numerico.");
			else System.out.println("No se ha podido iniciar el servidor, compruebe que el puerto esta disponible.");
		}

	}

}
