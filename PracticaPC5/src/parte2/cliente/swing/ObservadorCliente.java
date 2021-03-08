package parte2.cliente.swing;

import java.util.ArrayList;

import parte2.servidor.Usuario;


public interface ObservadorCliente {
	
	public void actualiza(String id, ArrayList<Usuario> listaUsuarios);
}