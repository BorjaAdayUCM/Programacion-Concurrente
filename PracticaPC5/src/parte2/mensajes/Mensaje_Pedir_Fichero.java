package parte2.mensajes;

import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Mensaje_Pedir_Fichero extends Mensaje {

	private String id, fichero, nombreGuardado;
	
	public Mensaje_Pedir_Fichero(String origen, String destino, TipoMensaje tipoMensaje, String id, String fichero, String nombreGuardado) {
		super(origen, destino, tipoMensaje);
		this.id = id;
		this.fichero = fichero;
		this.nombreGuardado = nombreGuardado;
	}

	@Override
	public String getId() throws OperationNotSupportedException {
		return this.id;
	}

	@Override
	public String getFichero() throws OperationNotSupportedException {
		return this.fichero;
	}

	@Override
	public String getNombreGuardado() {
		return nombreGuardado;
	}

}
