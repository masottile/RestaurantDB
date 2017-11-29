package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {

	private static int portNumber;
	private ServerSocket serverSocket;

	public static void main(String[] args) {
		portNumber = Integer.parseInt(args[0]);

		try {
			YelpDBServer dBServer = new YelpDBServer(portNumber);
			dBServer.listen();
		} catch (IOException e) {
			System.out.println("rip you");
		}
	}
	
	private YelpDBServer(int portNumber) throws IOException{
		this.portNumber = portNumber;
		serverSocket = new ServerSocket(portNumber);
	}
	
	private void listen() {
		//this listens for a new client 
	}
	/*
	 * private static ServerSocket serverSocket; private Socket clientSocket;
	 * 
	 * private static int portNumber; private static boolean isStopped = false;
	 * private static YelpDB theDB;
	 * 
	 * public static void main(String[] args) {
	 * 
	 * serverSocket = null;
	 * 
	 * portNumber = Integer.parseInt(args[0]);
	 * 
	 * // This block tries to initialize the things with the yelpDB wrapping stuffs
	 * initializeDB();
	 * 
	 * // This block opens up the server socket to listen at the given port number
	 * openServer();
	 * 
	 * // while the server is still running, it will listen for clients while
	 * (!isStopped) { // this block tries to accept a client request
	 * acceptClient(clientSocket); // TODO is this right?
	 * 
	 * // if the client is successful, then we create a new thread to deal with //
	 * whatever the fuck they want new Thread(new dBRunnable(clientSocket)).start();
	 * 
	 * }
	 * 
	 * // our server is no longer running System.out.println("Server stopped");
	 * 
	 * }
	 * 
	 * private static void initializeDB() { try { theDB = new
	 * YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json"); }
	 * catch (FileNotFoundException e) {
	 * System.out.println("fucked up the file names or some shit"); } }
	 * 
	 * private static void openServer() { try { serverSocket = new
	 * ServerSocket(portNumber); } catch (IOException e) {
	 * System.out.println("you fucked up with creating the server"); } }
	 * 
	 * private void acceptClient(Socket clientSocket) { try { clientSocket =
	 * serverSocket.accept(); } catch (IOException e) {
	 * System.out.println("you fucked up accepting the client"); } }
	 * 
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub
	 * System.out.println("YAY YOU");
	 * 
	 * BufferedReader in = new BufferedReader(new
	 * InputStreamReader(clientSocket.getInputStream())); PrintWriter out = new
	 * PrintWriter(clientSocket.getOutputStream()); // here is where we need to
	 * handle requests from the user.
	 * 
	 * }
	 */
}
