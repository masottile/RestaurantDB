package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

/**
 * "open" until the close() method is called, at which point it is "closed" and
 * may not be used further.
 */
public class Client2 {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Make a YelpClient, connect it to a server running on hostname at the
	 * specified port.
	 */
	public Client2(String hostname, int port) throws IOException {
		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void sendRequest(String s) throws IOException {
		out.print(s + "\n");
		out.flush();
	}

	public void sendLastRequest(String s) throws IOException {
		out.print(s);
		out.flush();
	}

	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException();
		}
		return reply;
	}

	/**
	 * Closes the client's connection to the server. This client is now "closed".
	 * Requires this is "open".
	 * 
	 * @throws IOException
	 *             if close fails
	 */
	public void close() throws IOException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			Client2 client = new Client2("localhost", YelpDBServer.YELP_PORT);
			client.sendRequest("QUERY in(Telegraph Ave) && category(Chinese)");
			client.sendLastRequest("QUERY price <= 2 && rating > 4");
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
