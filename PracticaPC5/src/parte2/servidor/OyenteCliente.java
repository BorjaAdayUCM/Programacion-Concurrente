package parte2.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.naming.OperationNotSupportedException;

import parte2.mensajes.Mensaje;
import parte2.mensajes.Mensaje_Confirmacion_Agregar_Ficheros;
import parte2.mensajes.Mensaje_Confirmacion_Cerrar_Conexion;
import parte2.mensajes.Mensaje_Confirmacion_Conexion;
import parte2.mensajes.Mensaje_Confirmacion_Eliminar_Ficheros;
import parte2.mensajes.Mensaje_Confirmacion_Lista_Usuarios;
import parte2.mensajes.Mensaje_Emitir_Fichero;
import parte2.mensajes.Mensaje_Error_Conexion;
import parte2.mensajes.Mensaje_Error_NoExisteFichero;
import parte2.mensajes.Mensaje_Preparado_ServidorCliente;
import parte2.mensajes.TipoMensaje;

public class OyenteCliente extends Thread {
	
	private Servidor servidor;
	private Socket socket;
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	
	public OyenteCliente(Socket socket, Servidor servidor) throws IOException {
		this.socket = socket;
		this.servidor = servidor;
		this.fin = new ObjectInputStream(this.socket.getInputStream());
		this.fout = new ObjectOutputStream(this.socket.getOutputStream());
	}
	
	public void run() {
		try {
			
			while(true) {
				Mensaje m = (Mensaje) this.fin.readObject();
				switch(m.getTipoMensaje()) {
					case MENSAJE_CONEXION: {
						if(!this.servidor.idValido(m.getId())) this.fout.writeObject(new Mensaje_Error_Conexion(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_ERROR_CONEXION));
						else {
							System.out.println("Se ha conectado el usuario: " + m.getId());
							this.servidor.addUsuario(new Usuario(m.getId(), m.getOrigen(), m.getListaFicheros()), new StreamUsuario(m.getId(), this.fin, this.fout));
							this.fout.writeObject(new Mensaje_Confirmacion_Conexion(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_CONFIRMACION_CONEXION));
						}
						break;
					}
					case MENSAJE_LISTA_USUARIOS : {
						System.out.println(m.getId() + " ha solicitado la lista de usuarios.");
						this.fout.writeObject(new Mensaje_Confirmacion_Lista_Usuarios(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_CONFIRMACION_LISTA_USUARIOS, this.servidor.getListaUsuarios(), m.getId()));
						break;
					}
					case MENSAJE_AGREGAR_FICHEROS : {
						System.out.println(m.getId() + " ha compartido nuevos ficheros.");
						this.servidor.agregarFicheros(m.getId(), m.getListaFicheros());
						this.fout.writeObject(new Mensaje_Confirmacion_Agregar_Ficheros(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_CONFIRMACION_AGREGAR_FICHEROS));
						break;
					}
					case MENSAJE_ELIMINAR_FICHEROS : {
						System.out.println(m.getId() + " ha dejado de compartir algunos ficheros.");
						this.servidor.eliminarFicheros(m.getId(), m.getListaFicheros());
						this.fout.writeObject(new Mensaje_Confirmacion_Eliminar_Ficheros(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_CONFIRMACION_ELIMINAR_FICHEROS));
						break;
					}
					case MENSAJE_PEDIR_FICHERO : {
						System.out.println("El usuario " + m.getId() + " ha solicitado un fichero.");
						Usuario usuario = this.servidor.getUsuario(m.getFichero());
						if(usuario == null || m.getNombreGuardado().indexOf("noDescargar") != -1) fout.writeObject(new Mensaje_Error_NoExisteFichero(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_ERROR_NOEXISTEFICHERO));
						else {
							ObjectOutputStream fout = this.servidor.getOutputStream(usuario.getId());
							fout.writeObject(new Mensaje_Emitir_Fichero(m.getOrigen(), usuario.getIp(), TipoMensaje.MENSAJE_EMITIR_FICHERO, m.getId(), this.servidor.getPathCompleta(m.getFichero()), m.getNombreGuardado()));
						}
						break;
					}
					case MENSAJE_PREPARADO_CLIENTESERVIDOR : {
						ObjectOutputStream fout = this.servidor.getOutputStream(m.getId());
						fout.writeObject(new Mensaje_Preparado_ServidorCliente(m.getOrigen(), m.getDestino(), TipoMensaje.MENSAJE_PREPARADO_SERVIDORCLIENTE, m.getIp(), m.getPuerto(), m.getNombreGuardado()));
						break;
					}
					case MENSAJE_CERRAR_CONEXION: {
						System.out.println(m.getId() + " se ha desconectado.");
						this.servidor.deleteUsuario(m.getId());
						this.fout.writeObject(new Mensaje_Confirmacion_Cerrar_Conexion(this.servidor.getIp(), m.getOrigen(), TipoMensaje.MENSAJE_CONFIRMACION_CERRAR_CONEXION));
						this.fout.close();
						return;
					}
					default: break;
				}
			}
			
		} catch (ClassNotFoundException | IOException | OperationNotSupportedException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
