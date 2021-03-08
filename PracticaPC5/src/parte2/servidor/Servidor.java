package parte2.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {
	
	private String ip;
	private int puerto;
	private ListaUsuarios listaUsuarios;
	private ListaStreamsUsuarios listaStreamsUsuarios;
	private ServerSocket socket;
	
	public Servidor(String ip, int puerto) throws IOException {
		this.ip = ip;
		this.puerto = puerto;
		this.listaUsuarios = new ListaUsuarios();
		this.listaStreamsUsuarios = new ListaStreamsUsuarios();
		this.socket = new ServerSocket(this.puerto);
		System.out.println("Servidor iniciado.\n");
	}
	
	public void run() {
		while(true) {
			try {
				Socket socketCliente = this.socket.accept();
				new OyenteCliente(socketCliente, this).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void addUsuario(Usuario usuario, StreamUsuario streamUsuario) throws InterruptedException {
		this.listaUsuarios.addUsuario(usuario);
		this.listaStreamsUsuarios.add(streamUsuario);
	}
	
	public void deleteUsuario(String id) throws InterruptedException {
		this.listaUsuarios.deleteUsuario(id);
		this.listaStreamsUsuarios.deleteUsuario(id);
	}
	
	public ArrayList<Usuario> getListaUsuarios() {
		return this.listaUsuarios.getListaUsuarios();
	}
	
	public String getPathCompleta(String fichero) {
		return this.listaUsuarios.getPathCompleta(fichero);
	}
	
	public Usuario getUsuario(String fichero) {
		return this.listaUsuarios.getUsuario(fichero);
	}
	
	public ObjectOutputStream getOutputStream(String id) {
		return this.listaStreamsUsuarios.getOutputStream(id);
	}
	
	public void agregarFicheros(String id, ArrayList<String> listaFicheros) {
		this.listaUsuarios.agregarFicheros(id, listaFicheros);
	}
	
	public void eliminarFicheros(String id, ArrayList<String> listaFicheros) {
		this.listaUsuarios.eliminarFicheros(id, listaFicheros);
	}
	
	public boolean idValido(String id) throws InterruptedException {
		return this.listaUsuarios.idValido(id);
	}

}
