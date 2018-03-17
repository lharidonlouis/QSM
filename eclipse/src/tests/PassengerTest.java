package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import line_management.Passenger;

public class PassengerTest {

	@Test
	public void testPassenger() {
		Passenger passengerTest = new Passenger(3, 4, 0);
		
		assertEquals("Wrong Id in Constructor", 4, passengerTest.getId());
		assertEquals("Wrong type in Constructor", 3, passengerTest.getType());
		assertEquals("Wrong type in Constructor", 0, passengerTest.getWay());
	}

	@Test
	public void testGetId() {
		Passenger passengerTest = new Passenger(3, 4, 0);
		
		assertEquals("GetId Failed", 4, passengerTest.getId());
	}

	@Test
	public void testGetType() {
		Passenger passengerTest = new Passenger(3, 4, 0);
		
		assertEquals("GetType Failed", 3, passengerTest.getType());
	}
	
	@Test
	public void testGetWay() {
		Passenger passengerTest = new Passenger(3, 4, 0);
		
		assertEquals("GetType Failed", 0, passengerTest.getWay());
	}

}
