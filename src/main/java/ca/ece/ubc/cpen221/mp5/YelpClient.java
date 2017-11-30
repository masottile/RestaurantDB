package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

/**
 * "open" until the close() method is called, at which point it is "closed" and
 * may not be used further.
 */
public class YelpClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Make a YelpClient, connect it to a server running on hostname at the
	 * specified port.
	 */
	public YelpClient(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void sendRequest(int x) throws IOException {
		out.print(x + "\n");
		out.flush(); // important! make sure x actually gets sent
	}

	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("o fok");
		}

		try {
			return reply;
		} catch (NumberFormatException nfe) {
			throw new IOException("dafuq is this reply: " + reply);
		}
	}

	/**
	 * Closes the client's connection to the server. This client is now "closed".
	 * Requires this is "open".
	 * 
	 * @throws IOException
	 *             if close fails
	 */
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			YelpClient client = new YelpClient("localhost", YelpDBServer.YELP_PORT);

			for (int x = 1; x <= 5; ++x) {
				client.sendRequest(x);
				System.out.println("What's the square of " + x + "?");
			}

			// collect the replies
			for (int x = 1; x <= 5; ++x) {
				String y = client.getReply();
				System.out.println("It's " + y);
			}

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
