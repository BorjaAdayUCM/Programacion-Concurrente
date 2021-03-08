package parte2.cliente;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receptor extends Thread {
	
	private String ip, fichero;
	private int puerto;
	
	public Receptor(String ip, int puerto, String fichero) throws IOException {
		this.ip = ip;
		this.puerto = puerto;
		this.fichero = fichero;
	}
	
	public void run() {
		try {
			Socket socket = new Socket(ip, puerto);
			InputStream fin = socket.getInputStream();
			int count;
			byte[] bytes = new byte[16 * 1024];
			if(fichero.indexOf("noDescargar") != -1) {
				while((count = fin.read(bytes)) > 0);
			}
			else {
				File file = new File(fichero);
				BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(file));
				while((count = fin.read(bytes)) > 0) fout.write(bytes, 0, count);
				fout.close();
			}
			fin.close();
			socket.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
