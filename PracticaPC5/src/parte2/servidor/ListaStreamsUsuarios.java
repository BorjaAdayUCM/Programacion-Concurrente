package parte2.servidor;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListaStreamsUsuarios {

	private ArrayList<StreamUsuario> listaStreamsUsuarios;
	
	public ListaStreamsUsuarios() {
		super();
		this.listaStreamsUsuarios = new ArrayList<StreamUsuario>();
	}

	public synchronized void add(StreamUsuario streamUsuario) {
		this.listaStreamsUsuarios.add(streamUsuario);
	}
	
	public synchronized void deleteUsuario(String id) {
		int i = 0;
		while(!this.listaStreamsUsuarios.get(i).getId().equalsIgnoreCase(id)) i++;
		if(i < this.listaStreamsUsuarios.size()) this.listaStreamsUsuarios.remove(i);
	}
	
	public synchronized ObjectOutputStream getOutputStream(String id) {
		int k = 0;
		while(!this.listaStreamsUsuarios.get(k).getId().equalsIgnoreCase(id)) k++;
		return this.listaStreamsUsuarios.get(k).getFout();
	}
}
