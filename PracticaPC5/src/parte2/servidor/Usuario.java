package parte2.servidor;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
	
	private String id;
	private String ip;
	private ArrayList<String> listaFicheros;

	public Usuario(String id, String ip, ArrayList<String> listaFicheros) {
		super();
		this.id = id;
		this.ip = ip;
		this.listaFicheros = new ArrayList<String>(listaFicheros);
	}
	
	public Usuario(Usuario usuario) {
		this.id = usuario.id;
		this.ip = usuario.ip;
		this.listaFicheros = new ArrayList<String>(usuario.listaFicheros);
	}

	public String getId() {
		return id;
	}
	
	public String getIp() {
		return ip;
	}
	
	public ArrayList<String> getListaFicheros() {
		return listaFicheros;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", ip=" + ip + ", listaFicheros=" + listaFicheros + "]";
	}
	
}
