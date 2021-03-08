package parte2.servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StreamUsuario {
	
	private String id;
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	
	public StreamUsuario(String id, ObjectInputStream fin, ObjectOutputStream fout) {
		super();
		this.id = id;
		this.fin = fin;
		this.fout = fout;
	}

	public String getId() {
		return id;
	}

	public ObjectInputStream getFin() {
		return fin;
	}

	public ObjectOutputStream getFout() {
		return fout;
	}
	
}
