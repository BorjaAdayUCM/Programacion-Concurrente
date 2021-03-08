package parte1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		if(args.length < 2) System.out.println("Necesario el host y puerto.");
		try {
			
			Socket socket;
			Scanner scanner = new Scanner(System.in);
			BufferedReader inC;
			PrintWriter outC;
			BufferedWriter bw;
			
			boolean cerrarConexion = false;
			
			while(!cerrarConexion) {
				
				socket = new Socket(args[0], Integer.parseInt(args[1]));
				scanner = new Scanner(System.in);
				inC = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outC = new PrintWriter(socket.getOutputStream(), true);
				
				System.out.print("Introduce el nombre de fichero que desea descargar: ");
				String peticionCliente = scanner.nextLine();
				outC.println(peticionCliente);
				
				String linea = inC.readLine();
				if(linea.equalsIgnoreCase("El archivo solicitado no existe en el servidor.")) System.out.println(linea);
				else {
					File fichero = new File (peticionCliente.substring(0, peticionCliente.length() - 4) + Math.random() + ".txt");
					if(fichero.createNewFile()) {
						bw = new BufferedWriter(new FileWriter(fichero));
						while(!linea.equalsIgnoreCase("EOF")) {
							bw.write(linea + '\n');
							linea = inC.readLine();
						}
						bw.close();
					} 
					else {
						System.out.println("No se ha podido crear el fichero.\nEs posible que disponga de un fichero con el mismo nombre.\n");
					}
				}
				
				inC.close();
				outC.close();
				
				System.out.print("¿Desea descargar algun fichero mas? (S/N): ");
				String respuesta = scanner.nextLine();
				while(!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N")) {
					System.out.println("No he entendido su respueta.\n");
					System.out.print("¿Desea descargar algun fichero mas? (S/N): ");
					respuesta = scanner.nextLine();
				}
				if(respuesta.equalsIgnoreCase("N")) cerrarConexion = true;
			}
		}
		catch (Exception e) {
			System.out.println("Existe algun problema con el servidor.");
		}

	}

}