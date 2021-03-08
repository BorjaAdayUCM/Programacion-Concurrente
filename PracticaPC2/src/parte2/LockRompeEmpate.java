package parte2;


public class LockRompeEmpate extends Lock {

	private volatile int[] in;
	private volatile int[] last;
	private int nThreads;
	
	public LockRompeEmpate(int numThreads)
	{
		this.nThreads = numThreads;
		this.in = new int[this.nThreads];
		this.last = new int[this.nThreads];		
		for (int i = 0; i < this.nThreads; i++) {
			this.in[i] = -1;
			this.last[i] = -1;
		}
		this.last = this.last; //Actualiza la referencia en memoria 
		this.in = this.in;
	}
	
	
	@Override
	void takeLock(int i) {
		for(int j = 0; j < this.nThreads; j++) {
			this.in[i] = j; this.last[j] = i;
			this.in = this.in; this.last = this.last;
			for(int k = 0; k < this.nThreads; k++) {
				if(k != i) {
					while(this.in[k] >= this.in[i] && this.last[j] == i);
				}
			}
		}
	}

	
	@Override
	void releaseLock(int i) {
		this.in[i] = -1;
		this.in = this.in;
	}

}
