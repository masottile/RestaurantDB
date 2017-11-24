package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDB;

public class YelpDBTests {

	@Test
	public void test0() {
		assertEquals(1, 1);
		assertTrue(true);
		assertFalse(false);
		//fail();
	}
	
	@Test
	public void test1() throws FileNotFoundException {
		YelpDB aiya = new YelpDB("d","d"," ");
		System.out.println(aiya.resSet.size());
		System.out.println(aiya.userSet.size());
		System.out.println(aiya.revSet.size());
		
	}

}
