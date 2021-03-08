package parte2.cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import parte2.Utilidades;
import parte2.mensajes.Mensaje_Agregar_Ficheros;
import parte2.mensajes.Mensaje_Cerrar_Conexion;
import parte2.mensajes.Mensaje_Conexion;
import parte2.mensajes.Mensaje_Lista_Usuarios;
import parte2.mensajes.Mensaje_Pedir_Fichero;
import parte2.mensajes.Mensaje_Preparado_ClienteServidor;
import parte2.mensajes.TipoMensaje;

public class Cliente {
	
	private ObjectOutputStream fout;
	private String ipServidor;
	private String id;
	private String ipCliente;
	private int puertoCliente;
	private int puertoServidor;
	private Scanner scanner;
	private Socket socket;
	private OyenteServidor oyenteServidor;
	private int numPuertos;
	private JFileChooser fc;
	private Semaphore semMenu;
	
	public Cliente(String[] args, boolean GUI) {
		try {
			ipCliente = args[0];
			puertoCliente = Integer.parseInt(args[1]);
			numPuertos = Integer.parseInt(args[2]);
			ipServidor = args[3];
			this.puertoServidor = Integer.parseInt(args[4]);
			this.fc = new JFileChooser(System.getProperty("user.home"));
			this.semMenu = new Semaphore(0);
			
			socket = new Socket(ipServidor, puertoServidor);
			fout = new ObjectOutputStream(socket.getOutputStream());
			scanner = new Scanner(System.in);
			
			if(GUI) this.runGUI();
			else this.runBatch();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse al servidor.", "Error", 0);
		}
	}
	
	public void runBatch() {
		try {
			
			System.out.print("¿Que nombre de usuario desea tener? ");
			this.id = scanner.nextLine();
			System.out.println();
			while(this.id == null || this.id.isEmpty()) {
            	System.out.println("Debe introducir un nombre de usuario valido.");
            	System.out.print("¿Que nombre de usuario desea tener? ");
    			this.id = scanner.nextLine();
            }
			
			
			this.enviarMensajeConexion(new Mensaje_Conexion(ipCliente, ipServidor, TipoMensaje.MENSAJE_CONEXION, this.id, new ArrayList<String>()));
			
			this.oyenteServidor = new OyenteServidor(socket, false, this, this.semMenu);
			this.oyenteServidor.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runGUI() {
		try {
			
			this.id = JOptionPane.showInputDialog("ID Cliente: ");
            while(this.id == null || this.id.isEmpty()) {
            	JOptionPane.showMessageDialog(null, "Debe introducir un nombre de usuario valido.", "Error", JOptionPane.ERROR_MESSAGE);
            	this.id = JOptionPane.showInputDialog("ID Cliente: ");
            }
			this.enviarMensajeConexion(new Mensaje_Conexion(ipCliente, ipServidor, TipoMensaje.MENSAJE_CONEXION, this.id, new ArrayList<String>()));
			
			this.oyenteServidor = new OyenteServidor(socket, true, this, this.semMenu);
			this.oyenteServidor.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int menu() {
		String op;
		System.out.println("0. Salir.");
		System.out.println("1. Listar Usuarios.");
		System.out.println("2. Compartir ficheros.");
		System.out.println("3. Dejar de compartir ficheros.");
		System.out.println("4. Solicitar Fichero.");
		
		System.out.print("Opcion: ");
		op = scanner.nextLine();
		System.out.println();
		
		while(!Utilidades.isNumber(op) || Integer.parseInt(op) < 0 || Integer.parseInt(op) > 4) {
			System.out.println("La opcion debe ser numerica entre 0 y 3.\n");
			System.out.print("Opcion: ");
			op = scanner.nextLine();
			System.out.println();
		}
		
		return Integer.parseInt(op);
	}
	
	public void ComienzaMenu() throws InterruptedException, IOException {
		int op = 0;
		do {
			this.semMenu.acquire();
			op = menu();
			switch(op) {
				case 0: this.cerrarConexion(); return;
				case 1: this.ObtenerListaUsuarios(); break;
				case 2: this.compartirFicheros(this.solicitaFicherosCompartir()); break;
				case 3: this.dejarCompartirFicheros(this.solicitaFicherosDejarCompartir()); break;
				case 4: this.descargarFicheroBatch(this.solicitarFichero()); break;
			}
		} while(true);
	}
	
	public void cerrarConexion() throws IOException {
		fout.writeObject(new Mensaje_Cerrar_Conexion(ipCliente, ipServidor, TipoMensaje.MENSAJE_CERRAR_CONEXION, id));
	}
	
	public void ObtenerListaUsuarios() throws IOException {
		fout.writeObject(new Mensaje_Lista_Usuarios(ipCliente, ipServidor, TipoMensaje.MENSAJE_LISTA_USUARIOS, id));
	}
	
	public ArrayList<String> solicitaFicherosCompartir() {
		ArrayList<String> ficherosAnadir = new ArrayList<String>();
		System.out.println("Introduce todos los ficheros que desea compartir (FIN): ");
		String fichero = scanner.nextLine();
		while(!fichero.equalsIgnoreCase("FIN")) {
			ficherosAnadir.add(fichero);
			fichero = scanner.nextLine();
		}
		return ficherosAnadir;
	}
	
	public void compartirFicheros(ArrayList<String> ficherosAnadir) throws IOException {
		fout.writeObject(new Mensaje_Agregar_Ficheros(ipCliente, ipServidor, TipoMensaje.MENSAJE_AGREGAR_FICHEROS, this.id, ficherosAnadir));
	}
	
	public ArrayList<String> solicitaFicherosDejarCompartir() {
		ArrayList<String> ficherosEliminar = new ArrayList<String>();
		System.out.println("Introduce todos los ficheros que desea dejar de compartir (FIN): ");
		String fichero = scanner.nextLine();
		while(!fichero.equalsIgnoreCase("FIN")) {
			ficherosEliminar.add(fichero);
			fichero = scanner.nextLine();
		}
		return ficherosEliminar;
	}
	
	public void dejarCompartirFicheros(ArrayList<String> ficherosEliminar) throws IOException {
		fout.writeObject(new Mensaje_Agregar_Ficheros(ipCliente, ipServidor, TipoMensaje.MENSAJE_ELIMINAR_FICHEROS, this.id, ficherosEliminar));
	}
	
	public String solicitarFichero() {
		System.out.print("Introduce el fichero que desea descargar: ");
		return scanner.nextLine();
	}
	
	public void descargarFicheroBatch(String fichero) throws IOException {
		System.out.print("¿Con que nombre desea guardar el fichero? ");
		String nombreGuardado = scanner.nextLine();
		fout.writeObject(new Mensaje_Pedir_Fichero(ipCliente, ipServidor, TipoMensaje.MENSAJE_PEDIR_FICHERO, this.id, fichero, nombreGuardado));
	}
	
	public void descargarFichero(String fichero) throws IOException {
		String nombreGuardado = "noDescargar";
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			nombreGuardado = fc.getSelectedFile().getAbsolutePath();
		}
		fout.writeObject(new Mensaje_Pedir_Fichero(ipCliente, ipServidor, TipoMensaje.MENSAJE_PEDIR_FICHERO, this.id, fichero, nombreGuardado));
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public int getPuertoCliente() {
		return puertoCliente;
	}
	
	public void enviarPreparadoClienteServidor(Mensaje_Preparado_ClienteServidor mensaje) throws IOException {
		fout.writeObject(mensaje);
	}
	
	public void enviarMensajeConexion(Mensaje_Conexion mensaje) throws IOException {
		fout.writeObject(mensaje);
	}

	public OyenteServidor getOyenteServidor() {
		return oyenteServidor;
	}

	public int getNumPuertos() {
		return numPuertos;
	}
	
	public String getIpServidor() {
		return ipServidor;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

}