//Borja Aday Guadalupe Luis
//Eduardo Bryan de Renovales Alvarado

package parte1;

public class MainParte1 {

	public static final int N = 5000;
	
	public static void main(String[] args) {
		
		VariableCompartida variableCompartida = new VariableCompartida();
		LockCompartido lockCompartido = new LockCompartido();
		
		Thread_Inc thread_inc = new Thread_Inc(N, variableCompartida, lockCompartido);
		Thread_Dec thread_dec = new Thread_Dec(N, variableCompartida, lockCompartido);
		
		thread_inc.start(); thread_dec.start();
		
		try {
			thread_inc.join();
			thread_dec.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	
		System.out.println(variableCompartida.getVariableCompartida());
	}
}
