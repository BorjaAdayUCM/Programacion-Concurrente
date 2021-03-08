package parte2.mensajes;

@SuppressWarnings("serial")
public class Mensaje_Cerrar_Conexion extends Mensaje {
	
	private String id;

	public Mensaje_Cerrar_Conexion(String origen, String destino, TipoMensaje tipoMensaje, String id) {
		super(origen, destino, tipoMensaje);
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getId() {
		return this.id;
	}

}
