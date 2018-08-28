import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;


public class MessageThread extends Thread {
	private Socket socket;

	public MessageThread(Socket socket) {
		this.socket = socket;
	}
	public void run(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
			Thread thread = new Thread( new Runnable() {	
				@Override
				public void run() {
					while(true){
						String readLine;
						try {
							readLine = reader.readLine();
							if (readLine == null) {
								break;
							}
							 System.out.println("client : " + readLine);
						} catch (IOException e) {
							System.out.println("Gặp sự cố kết nối");
							break;
						}
						
					}
				}
			});
			thread.start();
			while(true){
				if (socket.isClosed()) {
					break;
				}
				writer.write( new Scanner(System.in).nextLine());
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
