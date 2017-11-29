package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WelpDBServer {

	private ServerSocket serverSocket;
	private static int PORT_NUMBER = 5949;

	public static void main(String[] args) {
		try {
			WelpDBServer welp = new WelpDBServer(PORT_NUMBER);
			welp.serve();
		} catch (IOException e) {
		}
	}

	public WelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void serve() throws IOException {
		while (true) {
			final Socket socket = serverSocket.accept();

			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							talk(socket);
						} finally {
							socket.close();
						}
					} catch (IOException e) {
					}
				}
			});
			handler.start();
		}
	}

	private void talk(Socket socket) throws IOException {
		System.err.println("You're connected! type one word");
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				try {
					System.err.println("Well, there's really not much for me to say here");
					System.err.println("Here's whatever you inputted, printed three times: " + line + line + line);

				} catch (NumberFormatException e) {
					System.err.println("reply: please stop");
				}
			}
		} finally {
			out.close();
			in.close();
		}
	}
}
