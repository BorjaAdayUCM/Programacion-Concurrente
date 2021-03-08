package parte2.servidor;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListaUsuarios {
	
	private ArrayList<Usuario> listaUsuarios;

	public ListaUsuarios() {
		super();
		this.listaUsuarios = new ArrayList<Usuario>();
	}
	
	public synchronized void addUsuario(Usuario usuario) {
		this.listaUsuarios.add(usuario);
	}
	
	public synchronized void deleteUsuario(String id) {
		int i = 0;
		while(!this.listaUsuarios.get(i).getId().equalsIgnoreCase(id)) i++;
		if(i < this.listaUsuarios.size()) this.listaUsuarios.remove(i);
	}

	public synchronized ArrayList<Usuario> getListaUsuarios() {
		ArrayList<Usuario> nuevaLista = new ArrayList<Usuario>();
		for(Usuario u : this.listaUsuarios) nuevaLista.add(new Usuario(u));
		return nuevaLista;
	}
	
	public synchronized String getPathCompleta(String fichero) {
		for(Usuario u : this.listaUsuarios) {
			for(String fich : u.getListaFicheros()) {
				String[] folders = fich.split(Pattern.quote(File.separator));
				if(folders[folders.length - 1].equalsIgnoreCase(fichero)) return fich;
			}
		}
		return null;
	}
	
	public synchronized Usuario getUsuario(String fichero) {
		for(Usuario u : this.listaUsuarios) {
			for(String fich : u.getListaFicheros()) {
				String[] folders = fich.split(Pattern.quote(File.separator));
				if(folders[folders.length - 1].equalsIgnoreCase(fichero)) {
					return u;
				}
			}
		}
		return null;
	}
	
	public synchronized void agregarFicheros(String id, ArrayList<String> listaFicheros) {
		int k = 0;
		while(!this.listaUsuarios.get(k).getId().equalsIgnoreCase(id)) k++;
		for(String fichero : listaFicheros) {
			if(!this.listaUsuarios.get(k).getListaFicheros().contains(fichero)) this.listaUsuarios.get(k).getListaFicheros().add(new String(fichero));
		}
	}
	
	public synchronized void eliminarFicheros(String id, ArrayList<String> listaFicheros) {
		int k = 0;
		while(!this.listaUsuarios.get(k).getId().equalsIgnoreCase(id)) k++;
		for(String fichero1 : listaFicheros) {
			ArrayList<String> listaFicherosUsuario = new ArrayList<String>(this.listaUsuarios.get(k).getListaFicheros());
			for(String fichero2 : listaFicherosUsuario) {
				String[] folders = fichero2.split(Pattern.quote(File.separator));
				String ficheroEliminar = folders[folders.length - 1];
				if(ficheroEliminar.equalsIgnoreCase(fichero1)) this.listaUsuarios.get(k).getListaFicheros().remove(new String(fichero2));
			}
			
		}
	}
	
	public synchronized boolean idValido(String id) {
		for(int i = 0; i < this.listaUsuarios.size(); i++) {
			if(this.listaUsuarios.get(i).getId().equalsIgnoreCase(id)) {
				return false;
			}
		}
		return true;
	}

}
