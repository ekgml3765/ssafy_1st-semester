import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	public static void main(String[] args) {
		BookMgrImpl mgr = BookMgrImpl.getInstance();
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				Socket socket = serverSocket.accept();
				ServerThread st = new ServerThread(socket, mgr);
				st.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread {
	private Socket socket = null;
	private BookMgrImpl mgr;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public ServerThread(Socket socket, BookMgrImpl mgr) {
		this.socket = socket;
		this.mgr = mgr;
		try {
			// this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (true) {
				Msg m = (Msg) ois.readObject();
				if (m.code == 1)
					mgr.add(m.b);
				else if (m.code == 2) {
					List<Book> b = mgr.list();
					synchronized (b) {
						oos.writeObject(b);
					}
				}
				else if(m.code==3) {
					mgr.sell(m.b.getIsbn(),m.b.getQuantity());
				}
				else if(m.code==0) {
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}