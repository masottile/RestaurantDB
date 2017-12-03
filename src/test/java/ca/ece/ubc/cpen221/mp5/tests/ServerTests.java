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
				YelpDBServer.main(null);
			}
		});
		Thread testClient = new Thread(new Runnable() {
			public void run() {
				try {
					Client client = new Client("localhost", 4949);
					client.sendRequest("GETRESTAURANT t-xuA4yR02gud00gTS2iyw");
					assertEquals("Chinese Express", client.getReply());
					client.sendRequest("ADDUSER {\"name\":\"Jessica\"}");
					client.close();
				} catch (IOException e) {

				}
			}

		});
		testServer.start();
		testClient.start();

	}
}
