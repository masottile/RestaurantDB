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
		out.flush(); // important! make sure s actually gets sent
	}

	public void sendLastRequest(String s) throws IOException {
		out.print(s);
		out.flush(); // important! make sure s actually gets sent
	}

	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("o fok");
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
			client.sendRequest("ADDUSER {\"name\": \"Jessica\", \"review_count\": 2}");
			client.sendRequest("GETUSERINFO Bitch#1");
			client.sendRequest(
					"ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"text\": \"Well .... what can I say\", \"stars\": 2, \"user_id\": \"Bitch#1\", \"date\": \"2006-07-26\"}");
			client.sendRequest("GETUSERINFO Bitch#1");
			client.sendRequest(
					"ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"text\": \"Hello darkness, my old friend\", \"stars\": 4, \"user_id\": \"Bitch#1\", \"date\": \"2006-07-26\"}");
			client.sendRequest("GETUSERINFO Bitch#1");
			client.sendRequest("GETUSERINFO 754HGCLgGJLh1VU_WtGjsw");
			client.sendRequest(
					"ADDREVIEW {\"type\": \"review\", \"business_id\": \"1CBs84C-a-cuA3vncXVSAw\", \"text\": \"We don't need no education; teachers, leave those kids alone!! *this is not meant to be a subtle dig, I've just had this song stuck in my head all day*\", \"stars\": 4, \"user_id\": \"754HGCLgGJLh1VU_WtGjsw\", \"date\": \"2006-07-26\"}");
			client.sendLastRequest("GETUSERINFO 754HGCLgGJLh1VU_WtGjsw");
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
