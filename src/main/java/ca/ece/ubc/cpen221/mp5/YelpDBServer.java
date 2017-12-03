package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import com.google.gson.JsonParseException;
import ca.ece.ubc.cpen221.mp5.datatypes.*;

public class YelpDBServer {
	public static final int YELP_PORT = 4949;
	private YelpDB yelp;
	private ServerSocket serverSocket;

	private CountDownLatch addCount = new CountDownLatch(1);

	/**
	 * Creates a server listening on input port
	 * 
	 * @param port
	 *            number of port to listen to RI: 0 <= port <= 65535
	 */
	public YelpDBServer(int port) {
		try {
			yelp = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
			addCount.countDown();
		} catch (FileNotFoundException e1) {
		}
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
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

	public YelpDB getYelpDB() {
		return this.yelp;
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
							addCount.await();
						} catch (InterruptedException e) {
							System.out.println("aiyaa");
						}
						try {
							out.print(yelp.getRestNameFromId(info));
							out.flush();
						} catch (NullPointerException e) {
							out.println("ERR: NO_SUCH_RESTAURANT");
							out.flush();
						} finally {
							addCount = new CountDownLatch(1);
							addCount.countDown();
						}
						break;

					case "ADDUSER":
						try {
							addCount.await();
						} catch (InterruptedException e) {
						}
						addCount = new CountDownLatch(1);
						String s = yelp.addUser(info);
						out.println(s);
						out.flush();
						addCount.countDown();
						break;

					case "GETUSER":
						try {
							addCount.await();
						} catch (InterruptedException e) {
						}
						out.println(yelp.getUser(info).getName());
						out.flush();
						addCount.countDown();
						break;
						
					case "ADDRESTAURANT":
						try {
							addCount.await();
						} catch (InterruptedException e) {
						}
						addCount = new CountDownLatch(1);
						String y = yelp.addRestaurant(info);
						out.println(y);
						out.flush();
						addCount.countDown();
						break;

					case "ADDREVIEW":
						try {
							addCount.await();
						} catch (InterruptedException e) {
						}
						addCount = new CountDownLatch(1);
						out.println(yelp.addReview(info));
						addCount.countDown();
						break;

					case "QUERY":
						try {
							addCount.await();
						} catch (InterruptedException e) {
						}
						Set<YelpRestaurant> results = yelp.getMatches(info);
						if (results.isEmpty())
							out.println("ERR: NO_MATCH");
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
					out.println("ERR: INVALID_JSON_STRING");
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
			System.err.println("ERROR CREATING SERVER. Suggestion: check ports");
		}
	}
}
