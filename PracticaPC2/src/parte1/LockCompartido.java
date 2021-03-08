package parte1;

public class LockCompartido {
	
	private volatile boolean in1, in2;
	private volatile int last;
	public boolean isIn1() {
		return in1;
	}
	public void setIn1(boolean in1) {
		this.in1 = in1;
	}
	public boolean isIn2() {
		return in2;
	}
	public void setIn2(boolean in2) {
		this.in2 = in2;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	
}
