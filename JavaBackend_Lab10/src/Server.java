import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	private static int Port = 9090;
	public static void main(String[] args) {
		ExecutorService executorService= Executors.newFixedThreadPool(3);
		ServerSocket serversocket = null;
		Socket socket = null;
		try {
			serversocket = new ServerSocket(Port);
			System.out.println("Đang chờ Client kết nối tới...");	
			while(true){
				socket = serversocket.accept();
				System.out.println("Có client đang kết nối tới " + socket.getInetAddress().getHostAddress() + "-" + socket.getPort());
				MessageThread handler = new MessageThread(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				socket.close();
				serversocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
