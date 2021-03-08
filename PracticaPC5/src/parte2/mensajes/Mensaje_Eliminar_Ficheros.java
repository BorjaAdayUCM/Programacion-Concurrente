package parte2.mensajes;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Mensaje_Eliminar_Ficheros extends Mensaje {
	
	private String id;
	private ArrayList<String> listaFicheros;

	public Mensaje_Eliminar_Ficheros(String origen, String destino, TipoMensaje tipoMensaje, String id, ArrayList<String> listaFicheros) {
		super(origen, destino, tipoMensaje);
		this.id = id;
		this.listaFicheros = listaFicheros;
	}

	@Override
	public String getId() throws OperationNotSupportedException {
		return this.id;
	}

	@Override
	public ArrayList<String> getListaFicheros() throws OperationNotSupportedException {
		return this.listaFicheros;
	}
}
