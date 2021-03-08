package parte3;

import java.util.Random;

public class Matriz {
	
	private int N;
	private int[][] matriz;
	private Random rand = new Random();
	
	public Matriz(int N) {
		super();
		this.N = N;
		this.matriz = new int[this.N][this.N];
		for(int fila = 0; fila < this.N; fila++) {
			for(int columna = 0; columna < this.N; columna++) {
				this.matriz[fila][columna] = rand.nextInt(10);
			}
		}
	}
	
	public Integer getValor(int fila, int columna) {
		return this.matriz[fila][columna];
	}
	
	public void setValor(Integer valor, int fila, int columna) {
		this.matriz[fila][columna] = valor;
	}

	@Override
	public String toString() {
		String matriz = "";
		for(int fila = 0; fila < this.N; fila++) {
			for(int columna = 0; columna < this.N; columna++) {
				matriz += this.matriz[fila][columna] +  " ";
			}
			matriz += '\n';
		}
		return matriz.substring(0, matriz.length() - 1);
	}
	
	
	
}
