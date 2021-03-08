package parte2.cliente.swing;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import parte2.cliente.OyenteServidor;

@SuppressWarnings("serial")
public abstract class ModeloTabla<T> extends DefaultTableModel implements ObservadorCliente
{
	protected String[] columnIds; 
	protected List<T> lista;
	
	public ModeloTabla(String[] columnIdEventos, OyenteServidor oyenteServidor) { 
		this.lista = null;
		this.columnIds = columnIdEventos;
		oyenteServidor.addObservador(this);
	}
	
	@Override
	public boolean isCellEditable (int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) { 
		return this.columnIds[col]; 
	} 
	
	@Override
	public int getColumnCount(){
		return this.columnIds.length;
	} 
	
	@Override
	public int getRowCount() {
		return this.lista == null ? 0 : this.lista.size();
	}
}
