package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class YelpDBServer {
	/** Default port number where the server listens for connections. */
	public static final int YELP_PORT = 4949;

	private ServerSocket serverSocket;

	/**
	 * Makes server that listens for connections on some input port\
	 * 
	 * @param port
	 *            Rep invariant: 0 <= port <= 65535
	 */
	public YelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	/**
	 * Run the server, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		while (true) {
			// waits for a client to connect
			final Socket socket = serverSocket.accept();
			// create a new thread to handle that client
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			// starts a thread
			handler.start();
		}
	}

	/**
	 * Handle one client connection. Returns when client disconnects.
	 * 
	 * @param socket
	 *            socket where client is connected
	 * @throws IOException
	 *             if connection encounters an error
	 */
	private void handle(Socket socket) throws IOException {
		System.err.println("Connected to server!");

		// converts socket byte stream to character stream
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			System.err.println("enter an integer and get that integer squared");
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				try {
					int x = Integer.valueOf(line);
					int y = x * x;
					System.err.println("reply: " + y);
					out.println(y);

				} catch (NumberFormatException e) {
					System.err.println("please enter a valid integer");
					out.print("error\n");
				}
			}
		} finally {
			out.close();
			in.close();
		}
	}

	/**
	 * Start WelpDB on the default port = 4949
	 */
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELP_PORT);
			server.serve();
		} catch (IOException e) {
			System.out.println("main function not working");
			e.printStackTrace();
		}
	}
}
