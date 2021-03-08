package parte3;

public class Thread_Mul extends Thread {
	
	private int N;
	private int fila;
	private Matriz mat1;
	private Matriz mat2;
	private Matriz result;
	
	public Thread_Mul(int N, int fila, Matriz mat1, Matriz mat2, Matriz result) {
		super();
		this.N = N;
		this.fila = fila;
		this.mat1 = mat1;
		this.mat2 = mat2;
		this.result = result;
	}
	
	public void run() {
		for(int nVeces = 0; nVeces < this.N; nVeces++) {
			for(int i = 0; i < this.N; i++) {
				Integer val = 0;
				for(int j = 0; j < this.N; j++) {
					val += this.mat1.getValor(this.fila, j) * this.mat2.getValor(j, i);
				}
				this.result.setValor(val, fila, i);
			}
		}
	}
}
