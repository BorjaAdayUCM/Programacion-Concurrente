package parte2.cliente.swing;

public interface Observador<T> {
	public void addObservador(T o);
	public void removeObservador(T o); 
}
