package parte2.cliente.swing;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import parte2.cliente.OyenteServidor;
import parte2.servidor.Usuario;

@SuppressWarnings("serial")
public class ModeloTablaFicherosLocal extends ModeloTabla<String> {


	public ModeloTablaFicherosLocal(String[] columnIdEventos, OyenteServidor oyenteServidor) {
		super(columnIdEventos, oyenteServidor);
	}

	@Override // necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0: s = this.lista.get(indiceFil); break;
			default: assert (false);
		}
		return s; 
	}

	@Override
	public void actualiza(String id, ArrayList<Usuario> listaUsuarios) {
		lista = new ArrayList<String>();
		for(Usuario u: listaUsuarios) {
			if(u.getId().equalsIgnoreCase(id)) {
				for(String fichero : u.getListaFicheros()) {
					String[] folders = fichero.split(Pattern.quote(File.separator));
					lista.add(folders[folders.length - 1]);
				}
			}
		}
		this.fireTableStructureChanged();
	}

}
