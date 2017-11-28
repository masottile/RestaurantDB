package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {

	private static int portNumber;
	private static boolean isStopped = false;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		portNumber = Integer.parseInt(args[0]);

		// This block opens up the server socket to listen at the given port number
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println("you fucked up with creating the server");
		}

		// while the server is still running, it will listen for clients
		while (!isStopped) {

			// this block tries to accept a client request
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("you fucked up accepting the client");
			}
			// TODO is this right?

			// if the client is successful, then we create a new thread to deal with
			// whatever the fuck they want
			new Thread(new dBRunnable(clientSocket)).start();

		}
		
		// our server is no longer running
		System.out.println("Server stopped");

	}

	private static class dBRunnable implements Runnable {

		private Socket clientSocket;

		public dBRunnable(Socket client) {
			clientSocket = client;
			System.out.println("YAY YOU");
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("YAY YOU");
		}

	}
}
