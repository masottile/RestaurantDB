package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonParseException;

import ca.ece.ubc.cpen221.mp5.datatypes.*;

public class YelpDBServer {
	// Default port number where the server listens for connections. 
	public static final int YELP_PORT = 4999;
	private final YelpDB yelp;
	private ServerSocket serverSocket;

	/**
	 * Makes server that listens for connections on some input port\
	 * 
	 * @param port
	 *            0 <= port <= 65535
	 */
	public YelpDBServer(int port) {
		yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("ERROR CREATING SERVER. Suggestion: check ports");
		}

	}

	/**
	 * Run the server, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	private void serve() throws IOException {
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
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				try {
					String[] message = line.split(" ", 2);
					String function = message[0];
					String info = message[message.length - 1];

					switch (function) {
					case "GETRESTAURANT":
						try {
							out.print(yelp.getRestNameFromId(info));
							out.flush();
						} catch (NullPointerException e) {
							out.println("ERR: NO_SUCH_RESTAURANT");
							out.flush();
						}
						break;

					case "ADDUSER":
						String s = yelp.addUser(info);
						System.err.println(s);
						out.println(s);
						break;

					case "ADDRESTAURANT":
						out.println(yelp.addRestaurant(info));
						out.flush();
						break;

					case "ADDREVIEW":
						yelp.addReview(info);
						break;

					case "GETUSERRATINGINFO":
						YelpUser user = yelp.userMap.get(info);
						out.println(user.getAverageStars());
						out.flush();
						break;

					case "QUERY":
						out.println(yelp.getMatchesToJson(yelp.getMatches(info)));
						out.flush();
						break;

					default:
						out.println("ERR: ILLEGAL_REQUEST");
						out.flush();
						break;
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
	 * Start YelpDBServer on the default port
	 */
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELP_PORT);
			server.serve();
		} catch (IOException e) {
			System.out.println("ERROR CREATING SERVER. Suggestion: check ports");
		}
	}
}
