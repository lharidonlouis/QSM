package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import line_management.Passenger;

public class PassengerTest {

	@Test
	public void testPassenger() {
		Passenger passengerTest = new Passenger(0, 4);
		assertEquals("Wrong Id in Constructor", 4, passengerTest.getId());
		assertEquals("Wrong type in Constructor", 0, passengerTest.getType());
	}

	@Test
	public void testGetId() {
		Passenger passengerTest = new Passenger(0, 4);
		assertEquals("GetId Failed", 4, passengerTest.getId());
	}

	@Test
	public void testGetType() {
		Passenger passengerTest = new Passenger(0, 4);
		assertEquals("GetType Failed", 0, passengerTest.getType());
	}

}
