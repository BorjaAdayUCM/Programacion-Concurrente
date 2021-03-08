package parte2.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.naming.OperationNotSupportedException;
import javax.swing.JOptionPane;

import parte2.cliente.swing.Observador;
import parte2.cliente.swing.ObservadorCliente;
import parte2.cliente.swing.VentanaPrincipal;
import parte2.mensajes.Mensaje;
import parte2.mensajes.Mensaje_Conexion;
import parte2.mensajes.Mensaje_Preparado_ClienteServidor;
import parte2.mensajes.TipoMensaje;
import parte2.servidor.Usuario;

public class OyenteServidor extends Thread implements Observador<ObservadorCliente> {
	
	private Socket socket;
	private ObjectInputStream fin;
	private List<ObservadorCliente> observadores;
	private boolean GUI;
	private ArrayList<Semaphore> semaphoreEmisores;
	private Semaphore semMenu;
	private int puertoActual;
	private int numPuertos;
	private int puertoInicial;
	private static Cliente cliente;
	private Scanner scanner;
	
	public OyenteServidor(Socket socket, boolean GUI, Cliente clienteConstructor, Semaphore semMenu) throws IOException {
		cliente = clienteConstructor;
		this.socket = socket;
		this.fin = new ObjectInputStream(this.socket.getInputStream());
		this.observadores = new ArrayList<>();
		this.GUI = GUI;
		numPuertos = cliente.getNumPuertos();
		this.semaphoreEmisores = new ArrayList<Semaphore>();
		for(int i = 0; i < numPuertos; i++) this.semaphoreEmisores.add(new Semaphore(1));
		puertoInicial = cliente.getPuertoCliente();
		puertoActual = cliente.getPuertoCliente();
		this.scanner = new Scanner(System.in);
		this.semMenu = semMenu;
		
	}
	
	public void run() {
		if(this.GUI) this.runGUI();
		else this.runBatch();
	}
	
	public void runBatch() {
		try {
			
			while(true) {
				Mensaje m = (Mensaje) this.fin.readObject();
				switch(m.getTipoMensaje()) {
					case MENSAJE_ERROR_CONEXION: {
						System.out.println("El nombre de usuario ya existe en el servidor.");
						System.out.print("¿Que nombre de usuario desea tener? ");
						cliente.setId(this.scanner.nextLine());
						System.out.println();
			            cliente.enviarMensajeConexion(new Mensaje_Conexion(cliente.getIpCliente(), cliente.getIpServidor(), TipoMensaje.MENSAJE_CONEXION, cliente.getId(), new ArrayList<String>()));
						break;
					}
					case MENSAJE_CONFIRMACION_CONEXION: {
						System.out.println("Conexion establecida con exito.\n");
						new Thread() {
						     public void run() {
						    	 try {
									cliente.ComienzaMenu();
								} catch (InterruptedException | IOException e) {
									e.printStackTrace();
								}
						     }
						 }.start();
						this.semMenu.release();
						break;
					}
					case MENSAJE_CONFIRMACION_LISTA_USUARIOS : {
						ArrayList<Usuario> listaUsuarios = m.getListaUsuarios();
						for(Usuario u : listaUsuarios) {
							System.out.println(u.getId());
							for(String fichero : u.getListaFicheros()) {
								System.out.println(fichero);
							}
							System.out.println();
						}
						System.out.println();
						this.semMenu.release();
						break;
					}
					case MENSAJE_CONFIRMACION_AGREGAR_FICHEROS: {
						System.out.println("Ficheros compartidos correctamente.\n");
						this.semMenu.release();
						break;
					}
					case MENSAJE_CONFIRMACION_ELIMINAR_FICHEROS: {
						System.out.println("Ficheros dejados de compartir correctamente.\n");
						this.semMenu.release();
						break;
					}
					case MENSAJE_EMITIR_FICHERO : {
						new Emisor(puertoActual, m.getFichero(), this.semaphoreEmisores.get(this.puertoActual - puertoInicial), new Mensaje_Preparado_ClienteServidor(m.getDestino(), m.getOrigen(), TipoMensaje.MENSAJE_PREPARADO_CLIENTESERVIDOR, m.getId(), cliente.getIpCliente(),
								this.puertoActual, m.getFichero(), m.getNombreGuardado())).start();
						this.puertoActual = this.puertoInicial + (((this.puertoActual + 1) - this.puertoInicial) % this.numPuertos);
						break;
					}
					case MENSAJE_PREPARADO_SERVIDORCLIENTE : {
						new Receptor(m.getIp(), m.getPuerto(), m.getNombreGuardado()).start();
						this.semMenu.release();
						break;
					}
					case MENSAJE_ERROR_NOEXISTEFICHERO: {
						System.out.println("El fichero no existe en el servidor o has decidido no descargarlo.");
						this.semMenu.release();
						break;
					}
					case MENSAJE_CONFIRMACION_CERRAR_CONEXION: {
						System.out.println("Adios!");
						this.fin.close();
						this.socket.close();
						return;
					}
					
					default: break;
				}
			}
			
		} catch (ClassNotFoundException | IOException | OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public void runGUI() {
		try {
			
			while(true) {
				Mensaje m = (Mensaje) this.fin.readObject();
				switch(m.getTipoMensaje()) {
					case MENSAJE_CONFIRMACION_CONEXION: {
						java.awt.EventQueue.invokeLater(new Runnable() {
				            public void run() {
									try {
										new VentanaPrincipal(cliente).setVisible(true);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
				            }
				        });
						
						break;
					}
					case MENSAJE_ERROR_CONEXION: {
						JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe en el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
						cliente.setId(JOptionPane.showInputDialog("ID Cliente: "));
			            while(cliente.getId() == null || cliente.getId().isEmpty()) {
			            	JOptionPane.showMessageDialog(null, "Debe introducir un nombre de usuario valido.", "Error", JOptionPane.ERROR_MESSAGE);
			            	cliente.setId(JOptionPane.showInputDialog("ID Cliente: "));
			            }
			            cliente.enviarMensajeConexion(new Mensaje_Conexion(cliente.getIpCliente(), cliente.getIpServidor(), TipoMensaje.MENSAJE_CONEXION, cliente.getId(), new ArrayList<String>()));
						break;
					}
					case MENSAJE_CONFIRMACION_LISTA_USUARIOS : {
						ArrayList<Usuario> listaUsuarios = m.getListaUsuarios();
						this.notifica(m.getId(), listaUsuarios);
						break;
					}
					case MENSAJE_CONFIRMACION_AGREGAR_FICHEROS: break;
					case MENSAJE_CONFIRMACION_ELIMINAR_FICHEROS: break;
					case MENSAJE_EMITIR_FICHERO : {
						new Emisor(puertoActual, m.getFichero(), this.semaphoreEmisores.get(this.puertoActual - puertoInicial), new Mensaje_Preparado_ClienteServidor(m.getDestino(), m.getOrigen(), TipoMensaje.MENSAJE_PREPARADO_CLIENTESERVIDOR, m.getId(), cliente.getIpCliente(),
								this.puertoActual, m.getFichero(), m.getNombreGuardado())).start();
						this.puertoActual = this.puertoInicial + (((this.puertoActual + 1) - this.puertoInicial) % this.numPuertos);
						break;
					}
					case MENSAJE_PREPARADO_SERVIDORCLIENTE : {
						new Receptor(m.getIp(), m.getPuerto(), m.getNombreGuardado()).start();
						break;
					}
					case MENSAJE_ERROR_NOEXISTEFICHERO: break;
					case MENSAJE_CONFIRMACION_CERRAR_CONEXION: {
						this.fin.close();
						this.socket.close();
						return;
					}
					default: break;
				}
			}
			
		} catch (ClassNotFoundException | IOException | OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public static Cliente getCliente() {
		return cliente;
	}

	@Override
	public void addObservador(ObservadorCliente o) {
		if (o != null && !this.observadores.contains(o)) this.observadores.add(o);
	}

	@Override
	public void removeObservador(ObservadorCliente o) {
		if (o != null && this.observadores.contains(o)) this.observadores.remove(o);
	}
	
	private void notifica(String id, ArrayList<Usuario> listaUsuarios) {
		for (ObservadorCliente o : this.observadores) o.actualiza(id, listaUsuarios);
	}
}
