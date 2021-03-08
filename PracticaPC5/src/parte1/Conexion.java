package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Conexion extends Thread {
	
	Socket socket;
	BufferedReader inS;
	PrintWriter outS;
	BufferedReader br;
	
	public Conexion(Socket socket) throws IOException {
		this.socket = socket;
		this.inS = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.outS = new PrintWriter(socket.getOutputStream(), true);
		System.out.println("El cliente " + this.socket.getPort() + " se ha conectado.");
	}
	

	public void run() {
		try {
			String peticionCliente = this.inS.readLine();
			
			System.out.println("El cliente " + this.socket.getPort() + " ha solicitado el fichero: " + peticionCliente);
			
			File fichero = new File(peticionCliente);
			
			if(fichero.exists()) {
		        br = new BufferedReader(new FileReader(fichero));
		        String linea;
		        while((linea = br.readLine()) != null) outS.println(linea);
		        outS.println("EOF");
		        br.close();
		        System.out.println("El cliente " + this.socket.getPort() + " ha descargado el fichero: " + peticionCliente);
			}
			else outS.println("El archivo solicitado no existe en el servidor.");
			
			this.inS.close();
			this.outS.close();
			this.socket.close();
			
		} 
		catch(Exception e) {}
		
	}
	
}
