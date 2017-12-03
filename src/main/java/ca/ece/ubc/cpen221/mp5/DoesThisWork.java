package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;

public class DoesThisWork {
	public static void main(String[] args) throws InterruptedException {
		Thread testServer = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBServer server = new YelpDBServer(4949);
					server.serve();
				} catch (IOException e) {

				}
			}

		});
		testServer.start();
		testServer.join();

		Thread client2Test = new Thread(new Runnable() {
			public void run() {
				try {
					Client client1 = new Client("localhost", 4949);
					client1.sendRequest("ADDUSER {\"name\":\"Jessica\"}");
					System.out.println(client1.getReply());
					client1.sendRequest("ADDUSER {\"name\":\"Maria\"}");
					System.out.println(client1.getReply());
					client1.close();
				} catch (IOException e) {

				}
			}

		});
		client2Test.start();	}
}
