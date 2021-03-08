package parte2.mensajes;

@SuppressWarnings("serial")
public class Mensaje_Error_Conexion extends Mensaje {

	public Mensaje_Error_Conexion(String origen, String destino, TipoMensaje tipoMensaje) {
		super(origen, destino, tipoMensaje);
	}

}
