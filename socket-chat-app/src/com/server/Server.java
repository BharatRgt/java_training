package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket serverSocket;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void startServer() {
		try {
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				System.out.println("!!A new Client is connected!!");
				ClientHandler clientHandler = new ClientHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void closeServerSocket() {
		try {
			if(serverSocket!=null) {
				serverSocket.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(9090);
		Server server = new Server(serverSocket);
		server.startServer();
	}
}
