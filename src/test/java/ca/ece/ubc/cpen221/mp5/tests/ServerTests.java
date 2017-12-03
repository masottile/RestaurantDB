package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.Test;
import ca.ece.ubc.cpen221.mp5.*;

public class ServerTests {

	@Test
	public void test0() {

		Thread testServer = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Hello?1a");
					YelpDBServer server = new YelpDBServer(4949);
					System.out.println("Hello?1b");
					server.serve();
				} catch (IOException e) {
				}
			}

		});
		testServer.start();

		Thread testClient = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Hello?2a");
					Client client = new Client("localhost", 4949);
					System.out.println("Hello?2b");
					client.sendRequest("GETRESTAURANT t-xuA4yR02gud00gTS2iyw");
					System.out.println("Hello?2c");
					client.sendLastRequest("ADDUSER {\"name\":\"Jessica\"}");
					client.close();
				} catch (IOException e) {

				}
			}

		});
		testClient.start();

	}
}
