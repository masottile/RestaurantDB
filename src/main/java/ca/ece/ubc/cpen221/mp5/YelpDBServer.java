package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

import com.google.gson.JsonParseException;
import ca.ece.ubc.cpen221.mp5.datatypes.*;

public class YelpDBServer {
	public static final int YELP_PORT = 4999;
	private YelpDB yelp;
	private ServerSocket serverSocket;

	/**
	 * Creates a server listening on input port
	 * 
	 * @param port
	 *            number of port to listen to RI: 0 <= port <= 65535
	 */
	public YelpDBServer(int port) {
		try {
			yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
		} catch (FileNotFoundException e1) {
		}
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("ERROR CREATING SERVER. Suggestion: check ports");
		}
	}

	/**
	 * Creates threads and runs the server and processes requests
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		while (true) {
			final Socket socket = serverSocket.accept();
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
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
						out.println(s);
						out.flush();
						break;

					case "ADDRESTAURANT":
						String y = yelp.addRestaurant(info);
						out.println(y);
						out.flush();
						break;

					case "ADDREVIEW":
						out.println(yelp.addReview(info));
						break;

					case "GETUSERRATINGINFO":
						YelpUser user = yelp.getUserMap().get(info);
						out.print(
								"average stars: " + user.getAverageStars() + "total reviews: " + user.getReviewCount());
						out.flush();
						break;

					case "QUERY":
						Set<YelpRestaurant> results = yelp.getMatches(info);
						if (results.isEmpty())
							out.println("ERR: NO MATCH");
						else
							out.println(yelp.getMatchesToJson(yelp.getMatches(info)));
						out.flush();
						break;

					default:
						out.println("ERR: ILLEGAL_REQUEST");
						out.flush();
						break;
					}
				} catch (JsonParseException e) {
					out.println("ERR: INVALID_STRING");
				} catch (ArrayIndexOutOfBoundsException a) {
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
