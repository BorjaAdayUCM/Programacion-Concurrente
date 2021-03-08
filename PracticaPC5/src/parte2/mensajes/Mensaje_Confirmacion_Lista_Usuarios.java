package parte2.mensajes;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import parte2.servidor.Usuario;

@SuppressWarnings("serial")
public class Mensaje_Confirmacion_Lista_Usuarios extends Mensaje {
	
	private ArrayList<Usuario> listaUsuarios;
	private String id;

	public Mensaje_Confirmacion_Lista_Usuarios(String origen, String destino, TipoMensaje tipoMensaje, ArrayList<Usuario> listaUsuarios, String id) {
		super(origen, destino, tipoMensaje);
		this.listaUsuarios = listaUsuarios;
		this.id = id;
	}
	
	@Override
	public ArrayList<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}

	@Override
	public String getId() throws OperationNotSupportedException {
		return this.id;
	}
	
	

}
