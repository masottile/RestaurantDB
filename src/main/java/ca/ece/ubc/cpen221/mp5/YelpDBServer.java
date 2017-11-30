package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

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
	 * @throws BitchWhereException
	 */
	private void handle(Socket socket) throws IOException {
		YelpDB yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

		// converts socket byte stream to character stream
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {

				String[] message = line.split(" ", 2);

				try {
					String function = message[0];
					String info = message[1];

					if (function.equals("GETRESTAURANT")) {
						try {
							String name = yelp.getRestNameFromId(info);
							System.err.println("Name of " + info + " is: " + name);
							out.print(name);
						} catch (NullPointerException e) {
							System.err.println("ERR: NO_SUCH_RESTAURANT");
							out.println("ERR: NO_SUCH_RESTAURANT");
						}
					} else if (function.equals("ADDRESTAURANT")) {
						yelp.addRestaurant(info);
						System.err.println("Added the restaurant!");
					} else if (function.equals("ADDUSER")) {
						System.err.println(yelp.addUser(info));
						out.println(yelp.addUser(info));
						System.err.println("Added the user!");
					} else if (function.equals("ADDREVIEW")) {
						yelp.addReview(info);
						System.err.println("Added the review!");
					} else {
						System.err.println("ERR: ILLEGAL_REQUEST");
						out.println("ERR: ILLEGAL_REQUEST");
					}

				} catch (JsonParseException e) {
					System.err.println("ERR: INVALID_STRING");
					out.println("ERR: INVALID_STRING");
				} catch (ArrayIndexOutOfBoundsException a) {
					System.err.println("ERR: INVALID_REQUEST");
					out.println("ERR: INVALID_REQUEST");
				}
			}
		} finally {
			out.close();
			in.close();
		}
	}

	/**
	 * Start WelpDB on the default port
	 */
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELP_PORT);
			server.serve();
		} catch (IOException e) {
			System.out.println("main function not working");
		}
	}
}
