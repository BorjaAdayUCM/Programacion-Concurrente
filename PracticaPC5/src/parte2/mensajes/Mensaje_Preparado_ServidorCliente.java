package parte2.mensajes;

import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Mensaje_Preparado_ServidorCliente extends Mensaje {

	private String ip, nombreGuardado;
	private int puerto;
	
	public Mensaje_Preparado_ServidorCliente(String origen, String destino, TipoMensaje tipoMensaje, String ip, int puerto, String nombreGuardado) {
		super(origen, destino, tipoMensaje);
		this.puerto = puerto;
		this.nombreGuardado = nombreGuardado;
	}

	@Override
	public int getPuerto() throws OperationNotSupportedException {
		return this.puerto;
	}
	
	@Override
	public String getNombreGuardado() {
		return this.nombreGuardado;
	}
	
	@Override
	public String getIp() {
		return this.ip;
	}
	
}