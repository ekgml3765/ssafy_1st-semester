import java.io.Serializable;

public class Msg implements Serializable {
	int code;
	Book b;
	public Msg(int code, Book b) {
		super();
		this.code = code;
		this.b = b;
	}
	
	
}
