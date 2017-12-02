package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * "open" until the close() method is called, at which point it is "closed" and
 * may not be used further.
 */
public class CMDYelpClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Make a YelpClient, connect it to a server running on hostname at the
	 * specified port.
	 */
	public CMDYelpClient(String hostname, int port) throws IOException {

		socket = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	public void sendRequest(String s) throws IOException {
		out.print(s + "\n");
		out.flush(); // v important
	}

	public void sendLastRequest(String s) throws IOException {
		out.print(s);
		out.flush(); // v important
	}

	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("No reply? k.");
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

		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ", 2);
		String hostname = input[0];
		int port = Integer.valueOf(input[input.length - 1]);
		try {
			CMDYelpClient client = new CMDYelpClient(hostname, port);

			while (true) {
				client.sendRequest(sc.nextLine());

				if (sc.nextLine() == "last one") {
					client.sendLastRequest(sc.nextLine());
					break;
				}

				if (sc.nextLine() == "bye") {
					sc.close();
					client.close();
					break;
				}
			}

		} catch (IOException e) {
			System.err.println("You foked it up somehow, dunno how");
			e.printStackTrace();
		}
	}
}
