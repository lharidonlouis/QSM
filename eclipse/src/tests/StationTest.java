package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import line_management.Canton;
import line_management.Line;
import line_management.Passenger;
import line_management.Segment;
import line_management.Station;
import line_management.Train;

public class StationTest {
	int type = 3, capacity = 8, position = 1, id = 6, way = 0;
	int type2 = 2, capacity2 = 7, position2 = 5, id2 = 2, way2 = 0;
	
	String name = "StatTest1";
	
	Line lineTest = new Line();

	private Segment segmentTest = new Segment(0, 5, lineTest, 0);
	
	private Canton cantonTest = new Canton(segmentTest);
	
	private boolean terminus[] = {false, false}, start[] = {false, false}, backup = false;
	
	private Station stationTest = new Station("StatTest1", type, lineTest, capacity, position, id, backup, terminus, start);
	private Station stationTest2 = new Station("StatTest2", type2, lineTest, capacity2, position2, id2, backup, terminus, start);
	
	Train trainTest = new Train(0, 0, stationTest, 3, capacity);
	
	Passenger trainPassengerTest1 = new Passenger(0, 0, 0);
	Passenger trainPassengerTest2 = new Passenger(0, 1, 1);
	Passenger trainPassengerTest3 = new Passenger(5, 2, 0);
	
	Passenger stationPassengerTest1 = new Passenger(3, 5, 1);
	Passenger stationPassengerTest2 = new Passenger(0, 4, 0);
	Passenger stationPassengerTest3 = new Passenger(3, 5, 0);
	
	ArrayList<Passenger> passengersTest = new ArrayList<Passenger>();

	@Test
	public void testStation() {
		assertEquals("Name not initiate well", name, stationTest.getName());
		
		assertEquals("Type not initiate well", type, stationTest.getType());
		
		assertEquals("Line not initiate well", lineTest, stationTest.getLine());
		
		assertEquals("Capacity not initiate well", capacity, stationTest.getCapacity());
		
		assertEquals("Position not initiate well", position, stationTest.getPosition());
		
		assertEquals("Id not initiate well", id, stationTest.getId());
	}

	@Test
	public void testPickPassengers() {
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
		cantonTest.enter(trainTest, stationTest2);
		
		stationTest2.enter(trainTest);
		
		assertEquals("Enter failed to free previous Canton", null, trainTest.getCurrentCanton());
		
		assertEquals("Train failed to enter the station", stationTest2, trainTest.getCurrentStation());
		
		assertTrue("Enter failed to set Station as occupied", stationTest2.isTrackOccupied(trainTest.getWay()));
	}

	@Test
	public void testExit() {
		cantonTest.enter(trainTest, stationTest2);
		
		stationTest2.enter(trainTest);
		
		assertEquals("Train failed to enter the station", stationTest2, trainTest.getCurrentStation());
		
		stationTest2.exit(trainTest);
		
		assertEquals("Train failed to exit the station", null, trainTest.getCurrentStation());
		assertFalse("Enter failed to set Station as unoccupied", stationTest2.isTrackOccupied(trainTest.getWay()));
	}

	@Test
	public void testIsTrackOccupied() {
		assertFalse("Station set as occupied", stationTest2.isTrackOccupied(way));
		
		stationTest2.setTrackOccupiedTrue(way);
		
		assertTrue("Is occupied test Failed with true", stationTest2.isTrackOccupied(way));
	}

	@Test
	public void testSetTrackOccupiedTrue() {
		assertFalse("Station set as occupied", stationTest2.isTrackOccupied(way));
		
		stationTest2.setTrackOccupiedTrue(way);
		
		assertTrue("Failed to set occupied as true", stationTest2.isTrackOccupied(way));
	}

	@Test
	public void testSetTrackOccupiedFalse() {
		stationTest.setTrackOccupiedTrue(way);
		
		assertTrue("Failed to set occupied as true", stationTest.isTrackOccupied(way));
		
		stationTest.setTrackOccupiedFalse(way);
		
		assertFalse("Failed to set occupied as false", stationTest.isTrackOccupied(way));
		
	}

	@Test
	public void testGetPosition() {
		assertEquals("Station Position Getter went wrong", position, stationTest.getPosition());
	}

	@Test
	public void testGetLine() {
		assertEquals("Station Line Getter went wrong", lineTest, stationTest.getLine());
	}

	@Test
	public void testGetCapacity() {
		assertEquals("Station Capacity Getter went wrong", capacity, stationTest.getCapacity());
	}

	@Test
	public void testGetPassengers() {
		stationTest.getPassengers().add(stationPassengerTest1);
		
		stationTest.getPassengers().add(stationPassengerTest2);
		
		stationTest.getPassengers().add(stationPassengerTest3);
		
		assertEquals("Train Passengers missing", 0, trainTest.getPassengers().size());
		
		assertEquals("Station Passengers missing", 3, stationTest.getPassengers().size());
		
	}

	@Test
	public void testGetName() {
		assertEquals("Station id Getter went wrong", "StatTest1", stationTest.getName());
	}

	@Test
	public void testGetType() {
		assertEquals("Station type Getter went wrong", type, stationTest.getType());
	}

	@Test
	public void testGetId() {
		assertEquals("Station id Getter went wrong", id, stationTest.getId());
	}

	@Test
	public void testGetDescription() {
		assertEquals("Station Description Getter went wrong", "id : " + id + "\n\tposition : " + position + "\n\ttype : " + type + "\n\tcapacity : " + capacity, stationTest.getDescription());
	}

}
