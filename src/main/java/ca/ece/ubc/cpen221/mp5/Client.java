package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

/**
 * "open" until the close() method is called, at which point it is "closed" and
 * may not be used further.
 */
public class Client {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Make a YelpClient, connect it to a server running on hostname at the
	 * specified port.
	 */
	public Client(String hostname, int port) throws IOException {

		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

	}

	public void sendRequest(String s) throws IOException {
		out.print(s + "\n");
		out.flush(); // important! make sure s actually gets sent
	}

	public void sendLastRequest(String s) throws IOException {
		out.print(s);
		out.flush(); // important! make sure s actually gets sent
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
			Client client = new Client("localhost", YelpDBServer.YELP_PORT);
			client.sendRequest("GETRESTAURANT 93sW9Y_3rJQn305_n8epng");// Racha Cafe
			client.sendRequest("GETRESTAURANT MjHULXYJDc9XMM2r24oddg");// Berkeley Floor Cafe
			client.sendRequest("ADDUSER {\"name\": \"De Silva C.\"}");
			client.sendRequest(
					"ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"text\": \"I'm sick o dis shit\", \"stars\": 2, \"user_id\": \"90wm_01FAIqhcgV_mPON9Q\", \"date\": \"2006-07-26\"}");
			client.sendRequest("ADDUSER {\"review_count\": 5, \"name\": \"De Silva C.\"}");
			client.sendRequest("ADDUSER {\"name\": \"ddd\", \"review_count\": 5}");
			client.sendRequest("ADDUSER {\"name\": \"ccc\", \"review_count\": 5}");
			client.sendLastRequest(
					"ADDRESTAURANT {\"open\": true, \"longitude\": -420.000, \"neighborhoods\": [\"Telegraph Ave\", \"UC Campus Area\"], \"name\": \"Da Cribbb\", \"categories\": [\"Korean\", \"Restaurants\"], \"state\": \"CA\", \"type\": \"business\", \"stars\": 3.5, \"city\": \"Berkeley\", \"full_address\": \"2521 Durant Ave\\nSte F\\nTelegraph Ave\\nBerkeley, CA 94704\", \"schools\": [\"University of California at Berkeley\"], \"latitude\": 66.00, \"price\": 2}\r\n");
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
