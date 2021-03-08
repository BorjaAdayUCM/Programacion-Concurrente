package parte2;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket extends Lock {
	
	private volatile int nowTicket;
	private volatile AtomicInteger  nextTicket;
	
	public LockTicket()
	{
		this.nowTicket = 0;
		this.nextTicket = new AtomicInteger(0);
	}

	@Override
	void takeLock(int id) {
		int myTicket = this.nextTicket.getAndAdd(1);
		while(this.nowTicket != myTicket);
	}

	@Override
	void releaseLock(int id) {
		this.nowTicket++;
	}

}
