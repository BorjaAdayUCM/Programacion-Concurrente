package parte2;

abstract public class Lock {
	
	public Lock() {
		super();
	}
	
	abstract void takeLock(int id);
	
	abstract void releaseLock(int id);
}
