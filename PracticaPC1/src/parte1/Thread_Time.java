package parte1;

public class Thread_Time extends Thread{
	
	private int id;
	private int T;
	
	public Thread_Time(int id, int T) {
		this.id = id;
		this.T = T;
	}
	
	public void run() {
		System.out.println(id);
		try {
			Thread_Time.sleep(T);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(id);
	}
}
