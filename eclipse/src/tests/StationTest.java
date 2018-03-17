package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import line_management.Canton;
import line_management.Line;
import line_management.Passenger;
import line_management.Station;
import line_management.Train;

public class StationTest {

	@Test
	public void testStation() {
		int type = 3, capacity = 8, position = 1, id = 6;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		
		assertEquals("Name not initiate well", name, stationTest.getName());
		
		assertEquals("Type not initiate well", type, stationTest.getType());
		
		assertEquals("Line not initiate well", lineTest, stationTest.getLine());
		
		assertEquals("Capacity not initiate well", capacity, stationTest.getCapacity());
		
		assertEquals("Position not initiate well", position, stationTest.getPosition());
		
		assertEquals("Id not initiate well", id, stationTest.getId());
	}

	@Test
	public void testPickPassengers() {
		/*
		 * Test Fail: NullPointerException on add(Passenger)
		 */
		int type = 0, capacity = 12, position = 12, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, capacity);
		
		Passenger trainPassengerTest1 = new Passenger(0, 0, 0);
		Passenger trainPassengerTest2 = new Passenger(0, 1, 1);
		Passenger trainPassengerTest3 = new Passenger(5, 2, 0);
		
		Passenger stationPassengerTest1 = new Passenger(3, 5, 1);
		Passenger stationPassengerTest2 = new Passenger(0, 4, 0);
		Passenger stationPassengerTest3 = new Passenger(3, 5, 0);
		
		trainTest.getPassengers().add(trainPassengerTest1);
		trainTest.getPassengers().add(trainPassengerTest2);
		trainTest.getPassengers().add(trainPassengerTest3);
		
		stationTest.getPassengers().add(stationPassengerTest1);
		stationTest.getPassengers().add(stationPassengerTest2);
		stationTest.getPassengers().add(stationPassengerTest3);
		
		assertEquals("Train Passengers missing", 3, trainTest.getPassengers().size());
		
		assertEquals("Station Passengers missing", 3, stationTest.getPassengers().size());
		
		stationTest.pickPassengers(trainTest);
	}

	@Test
	public void testEnter() {
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		
		cantonTest.enter(trainTest);
		
		stationTest.enter(trainTest);
		
		assertEquals("Enter failed to free previous Canton", null, trainTest.getCurrentCanton());
		
		assertEquals("Train failed to enter the station", stationTest, trainTest.getCurrentStation());
		
		assertTrue("Enter failed to set Station as occupied", stationTest.isTrackOccupied(trainTest.getWay()));
	}

	@Test
	public void testExit() {
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		
		cantonTest.enter(trainTest);
		
		stationTest.enter(trainTest);
		
		assertEquals("Train failed to enter the station", stationTest, trainTest.getCurrentStation());
		
		stationTest.exit(trainTest);
		
		assertEquals("Train failed to exit the station", null, trainTest.getCurrentStation());
		assertFalse("Enter failed to set Station as unoccupied", stationTest.isTrackOccupied(trainTest.getWay()));
	}

	@Test
	public void testIsTrackOccupied() {
		int type = 0, capacity = 0, position = 12, id = 0, way = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertFalse("Station set as occupied", stationTest.isTrackOccupied(way));
		
		stationTest.setTrackOccupiedTrue(way);
		
		assertTrue("Is occupied test Failed with true", stationTest.isTrackOccupied(way));
	}

	@Test
	public void testSetTrackOccupiedTrue() {
		int type = 0, capacity = 0, position = 12, id = 0, way = 1;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertFalse("Station set as occupied", stationTest.isTrackOccupied(way));
		
		stationTest.setTrackOccupiedTrue(way);
		
		assertTrue("Failed to set occupied as true", stationTest.isTrackOccupied(way));
	}

	@Test
	public void testSetTrackOccupiedFalse() {
		int type = 0, capacity = 0, position = 12, id = 0, way = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		stationTest.setTrackOccupiedTrue(way);
		
		assertTrue("Failed to set occupied as true", stationTest.isTrackOccupied(way));
		
		stationTest.setTrackOccupiedFalse(way);
		
		assertFalse("Failed to set occupied as false", stationTest.isTrackOccupied(way));
		
	}

	@Test
	public void testGetPosition() {
		int type = 0, capacity = 12, position = 1, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station Position Getter went wrong", position, stationTest.getPosition());
	}

	@Test
	public void testGetLine() {
		int type = 0, capacity = 10, position = 0, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station Line Getter went wrong", lineTest, stationTest.getLine());
	}

	@Test
	public void testGetCapacity() {
		int type = 0, capacity = 5, position = 3, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station Capacity Getter went wrong", capacity, stationTest.getCapacity());
	}

	@Test
	public void testGetPassengers() {
		int type = 0, capacity = 12, position = 12, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1", type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, capacity);
		ArrayList<Passenger> passengersTest = new ArrayList<Passenger>();
		
		Passenger stationPassengerTest1 = new Passenger(3, 5, 0);
		Passenger stationPassengerTest2 = new Passenger(0, 4, 1);
		Passenger stationPassengerTest3 = new Passenger(3, 5, 1);
		
		stationTest.getPassengers().add(stationPassengerTest1);
		
		stationTest.getPassengers().add(stationPassengerTest2);
		
		stationTest.getPassengers().add(stationPassengerTest3);
		
		assertEquals("Train Passengers missing", 0, trainTest.getPassengers().size());
		
		assertEquals("Station Passengers missing", 3, stationTest.getPassengers().size());
		
	}

	@Test
	public void testGetName() {
		int type = 0, capacity = 8, position = 0, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station id Getter went wrong", "StatTest1", stationTest.getName());
	}

	@Test
	public void testGetType() {
		int type = 0, capacity = 6, position = 1, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station type Getter went wrong", type, stationTest.getType());
	}

	@Test
	public void testGetId() {
		int type = 0, capacity = 10, position = 2, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station id Getter went wrong", id, stationTest.getId());
	}

	@Test
	public void testGetDescription() {
		int type = 0, capacity = 120, position = 4, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		assertEquals("Station Description Getter went wrong", "id : " + id + "\n\tposition : " + position + "\n\ttype : " + type + "\n\tcapacity : " + capacity, stationTest.getDescription());
	}

}
