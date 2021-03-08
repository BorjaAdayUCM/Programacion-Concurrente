package parte2;

public class LockBakery extends Lock {
	
	private volatile int turno [];
	private int nThreads;
	
	public LockBakery(int nThreads) {
		this.nThreads = nThreads;
		this.turno = new int[this.nThreads];
		for(int i = 0; i < this.nThreads; i++)
			this.turno[i] = 1;
		this.turno = this.turno;
	}

	@Override
	void takeLock(int id) {
		int max = 1;
		for(int i = 0; i < this.nThreads; i++) {
			max = Math.max(max, this.turno[i]);
		}
		this.turno[id] = max + 1;
		this.turno =  this.turno;
		for(int j = 0; j < this.nThreads; j++) {
			if(id != j) {
				while(this.turno[j] != 0 && ((turno[id] > turno[j]) || (turno[id] == turno[j] && id > j)));
			}
		}
	}

	@Override
	void releaseLock(int id) {
		this.turno[id] = 0;
		this.turno = this.turno;
	}

}
