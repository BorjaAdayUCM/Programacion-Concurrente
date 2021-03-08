package parte2.mensajes;

import java.io.Serializable;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import parte2.servidor.Usuario;

@SuppressWarnings("serial")
public abstract class Mensaje implements Serializable{
	
	private String origen, destino;
	private TipoMensaje tipoMensaje;
	
	public Mensaje(String origen, String destino, TipoMensaje tipoMensaje) {
		this.origen = origen;
		this.destino = destino;
		this.tipoMensaje = tipoMensaje;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}
	
	public String getId() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	
	public ArrayList<String> getListaFicheros() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	public ArrayList<Usuario> getListaUsuarios() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	
	public String getFichero() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	
	public String getIp() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	
	public int getPuerto() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	public String getNombreGuardado() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	
}
