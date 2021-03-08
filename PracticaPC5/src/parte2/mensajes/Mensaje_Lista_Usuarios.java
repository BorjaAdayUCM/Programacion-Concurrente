package parte2.mensajes;

import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Mensaje_Lista_Usuarios extends Mensaje {
	
	private String id;

	public Mensaje_Lista_Usuarios(String origen, String destino, TipoMensaje tipoMensaje, String id) {
		super(origen, destino, tipoMensaje);
		this.id = id;
	}

	@Override
	public String getId() throws OperationNotSupportedException {
		return this.id;
	}
	
}
