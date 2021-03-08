package parte2.mensajes;

import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Mensaje_Preparado_ClienteServidor extends Mensaje {

	private String id, ip, fichero, nombreGuardado;
	private int puerto;
	
	public Mensaje_Preparado_ClienteServidor(String origen, String destino, TipoMensaje tipoMensaje, String id, String ip, int puerto, String fichero, String nombreGuardado) {
		super(origen, destino, tipoMensaje);
		this.id = id;
		this.puerto = puerto;
		this.ip = ip;
		this.fichero = fichero;
		this.nombreGuardado = nombreGuardado;
	}

	@Override
	public String getId() throws OperationNotSupportedException {
		return this.id;
	}

	@Override
	public String getIp() throws OperationNotSupportedException {
		return this.ip;
	}

	@Override
	public int getPuerto() throws OperationNotSupportedException {
		return this.puerto;
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
