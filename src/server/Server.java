package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket;
		final int PORT = 1001;
		
		try {
			serverSocket = new ServerSocket(PORT);
			
			while(true) {
				System.out.println("Waiting for the Client...");
				Socket socket = serverSocket.accept();
				
				Game game = new Game(socket);
				game.start();
			}
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}

}
