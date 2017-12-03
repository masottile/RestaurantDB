package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import ca.ece.ubc.cpen221.mp5.*;

public class ServerTests {
	@Test
	public void test0() throws InterruptedException {

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

		Thread client2Test = new Thread(new Runnable() {
			public void run() {
				try {
					Client client1 = new Client("localhost", 4949);
					client1.sendRequest("ADDUSER {\"name\":\"Jessica\"}");
					assertEquals(
							"{\"url\":\"http://www.yelp.com/user_details?userid\\u003dGenID#1\",\"votes\":{\"cool\":0,\"useful\":0,\"funny\":0},\"review_count\":0,\"type\":\"user\",\"user_id\":\"GenID#1\",\"name\":\"Jessica\",\"average_stars\":0.0}",
							client1.getReply());
					client1.sendRequest("ADDUSER {\"name\":\"Maria\"}");
					client1.getReply();
					client1.sendRequest("GETUSER GenID#2");
					assertEquals("Maria", client1.getReply());
					client1.close();
				} catch (IOException e) { 
				}
			}

		});
		client2Test.start();
		client2Test.join();

	}
}
