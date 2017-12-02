package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonParseException;

import ca.ece.ubc.cpen221.mp5.datatypes.YelpUser;

public class YelpDBServer {
	/** Default port number where the server listens for connections. */
	public static final int YELP_PORT = 4999;
	final YelpDB yelp;
	private ServerSocket serverSocket;

	/**
	 * Makes server that listens for connections on some input port\
	 * 
	 * @param port
	 *            Rep invariant: 0 <= port <= 65535
	 */
	public YelpDBServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");

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
							String name = yelp.getRestNameFromId(info);
							System.err.println("Name of " + info + " is: " + name);
							out.print(name);
						} catch (NullPointerException e) {
							System.err.println("ERR: NO_SUCH_RESTAURANT");
							out.println("ERR: NO_SUCH_RESTAURANT");
						}
						break;
					case "ADDUSER":
						String s = yelp.addUser(info);
						System.err.println(s);
						out.println(s);
						System.err.println("Added the user!");
						break;

					case "ADDRESTAURANT":
						yelp.addRestaurant(info);
						System.err.println("Added the restaurant!");
						break;

					case "ADDREVIEW":
						yelp.addReview(info);
						System.err.println("Added the review!");
						break;
						
					case "GETUSERINFO":
						YelpUser user = yelp.userMap.get(info);
						System.err.println("Average stars: " + user.getAverageStars());
						System.err.println("Number of reviews: " + user.getReviewCount());
						break;

					case "QUERY":
						System.err.println(yelp.getMatches(info));
						break;

					default:
						System.err.println("ERR: ILLEGAL_REQUEST");
						out.println("ERR: ILLEGAL_REQUEST");
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
			System.out.println("ERROR CREATING SERVER: CHECK PORTS MAYBE");
		}
	}
}
