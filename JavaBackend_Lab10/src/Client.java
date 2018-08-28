import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			System.out.println("Dang ket noi toi Server ...");
			 socket = new Socket("localhost",9090);
			System.out.println("Ket noi thanh cong toi Server"+ socket.getPort() );
			//System.out.println("Ket noi thanh cong toi Server"+ socket.getInetAddress() );
			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Thread thread = new Thread( new Runnable() {
				
				@Override
				public void run() {
					while(true){	
						try {
							String readLine = reader.readLine();
							if (readLine == null) {
								break;
							}
							 System.out.println("Server : " + readLine);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					
				}
			});
			thread.start();	
			while(true){
				if (socket.isClosed()) {
					break;
				}
				String s = new Scanner(System.in).nextLine();
				writer.write(s);
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
