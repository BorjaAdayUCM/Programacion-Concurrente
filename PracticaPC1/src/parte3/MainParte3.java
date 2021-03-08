package parte3;

import java.util.ArrayList;

public class MainParte3 {
	
	public static final int N = 3;
	
	public static void main(String[] args) {
		
		Matriz mat1 = new Matriz(N);
		Matriz mat2 = new Matriz(N);
		Matriz result = new Matriz(N);
		ArrayList<Thread_Mul> listThreads = new ArrayList<Thread_Mul>();
		
		for(int i = 0; i < N; i++) {
			listThreads.add(new Thread_Mul(N, i, mat1, mat2, result));
		}
		
		for(int i = 0; i < N; i++) {
			listThreads.get(i).start();
		}
		
		for(int i = 0; i < N; i++) {
			try {
				listThreads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(mat1 + "\n\n" + mat2 + "\n\n" + result);
	}
}
