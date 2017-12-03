package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.Test;
import ca.ece.ubc.cpen221.mp5.*;

public class ServerTests {

	@Test
	public void test0() {
		System.out.println("Did we even get here?");
		YelpDBServer server = new YelpDBServer(4949);
		System.out.println("Hello???");
		try {
			server.serve();
			System.out.println("tried to serve");
			Client client = new Client("localhost", 4949);
			System.out.println("created client");
			client.sendRequest("GETRESTAURANT t-xuA4yR02gud00gTS2iyw");
			System.out.println("sent request");
			client.sendLastRequest("ADDUSER {\"name\":\"Jessica\"}");
			client.close();
		} catch (IOException e) {
			System.out.println("IOexception oh oh");
		}

	}
}
