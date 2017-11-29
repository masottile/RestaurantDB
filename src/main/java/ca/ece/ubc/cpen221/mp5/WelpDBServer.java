package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WelpDBServer {
	/** Default port number where the server listens for connections. */
	public static final int PORT = 4949;

	private ServerSocket serverSocket;

	public WelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void serve() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			try {
				handle(socket);
			} catch (IOException ioe) {
				System.out.println("IOException just happened");

			} finally {
				socket.close();
			}
		}
	}

	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				try {
					System.err.println("Well, I took your request and tried to do something with it");
				} catch (NumberFormatException e) {
				}
				out.flush();
			}
		} finally {
			out.close();
			in.close();
		}
	}

	public static void main(String[] args) {
		try {
			WelpDBServer server = new WelpDBServer(PORT);
			server.serve();
		} catch (IOException e) {
			System.out.println("Server creation fucked up");
			e.printStackTrace();
		}
	}
}
