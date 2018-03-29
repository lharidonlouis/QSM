package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import line_management.Canton;
import line_management.Line;
import line_management.Segment;
import line_management.Station;
import line_management.Train;
import passengers.Passenger;

public class StationTest {
	private int type = 0, capacity = 8, position = 1, id = 6, way = 0;
	private int type2 = 2, capacity2 = 7, position2 = 5, id2 = 2;
	
	private String name = "StatTest1";
	
	private Line lineTest = new Line();

	private Segment segmentTest = new Segment(0, 5, lineTest, 0);
	
	private Canton cantonTest = new Canton(segmentTest);
	
	private boolean terminus[] = {false, true}, start[] = {true, false}, backup1 = false, backup2 = true;
	
	private Station stationTest = new Station("StatTest1", type, lineTest, capacity, position, id, backup1, terminus, start);
	private Station stationTest2 = new Station("StatTest2", type2, lineTest, capacity2, position2, id2, backup2, terminus, start);

	private Train trainTest = new Train(0, 0, stationTest, 3, capacity);
	
	private Passenger trainPassengerTest1 = new Passenger(0, 0, 0);
	private Passenger trainPassengerTest2 = new Passenger(0, 1, 1);
	private Passenger trainPassengerTest3 = new Passenger(5, 2, 0);
	
	private Passenger stationPassengerTest1 = new Passenger(3, 5, 1);
	private Passenger stationPassengerTest2 = new Passenger(0, 4, 0);
	private Passenger stationPassengerTest3 = new Passenger(3, 5, 1);
	
	private ArrayList<Passenger> passengersTest = new ArrayList<Passenger>();
	private ArrayList<Passenger> passengersTest2 = new ArrayList<Passenger>();

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
		
		stationTest.addPassenger(stationPassengerTest1);
		stationTest.addPassenger(stationPassengerTest2);
		stationTest.addPassenger(stationPassengerTest3);
		
		passengersTest.add(trainPassengerTest2);
		passengersTest.add(trainPassengerTest3);
		passengersTest.add(stationPassengerTest2);
		
		passengersTest2.add(stationPassengerTest1);
		passengersTest2.add(stationPassengerTest3);
		
		stationTest.pickPassengers(trainTest);
		
		assertEquals("Station.pickPassengers failed to empty train", passengersTest, trainTest.getPassengers());

		assertEquals("Station.pickPassengers failed to fill station", passengersTest2, stationTest.getPassengers());
	}
	
	@Test
	public void testAddPassenger() {
		stationTest.addPassenger(stationPassengerTest1);
		stationTest.addPassenger(stationPassengerTest2);
		stationTest.addPassenger(stationPassengerTest3);
		
		assertEquals("Station Passengers missing", 3, stationTest.getPassengers().size());
	}

	@Test
	public void testEnter() {
		cantonTest.enter(trainTest, stationTest2);
		
		stationTest2.enter(trainTest);
		
		assertFalse("Train entered canton without exiting station", stationTest.isTrackOccupied(trainTest.getWay()));
		
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
	public void testIsBackup() {
		assertFalse("Station.isBackup went wrong", stationTest.isBackup());
		assertTrue("Station.isBackup went wrong", stationTest2.isBackup());
	}
	
	@Test
	public void testIsTerminus() {
		assertFalse("Station.isTerminus went wrong", stationTest.isTerminus(0));
		assertTrue("Station.isTerminus went wrong", stationTest2.isTerminus(1));
	}
	
	@Test
	public void testisStart() {
		assertFalse("Station.isStart went wrong", stationTest2.isStart(1));
		assertTrue("Station.isStart went wrong", stationTest.isStart(0));
	}	

	@Test
	public void testSetStart() {
		assertFalse("Station.setStart went wrong", stationTest2.isStart(1));
		
		stationTest2.setStart(1, true);
		
		assertTrue("Station.setStart went wrong", stationTest2.isStart(1));
	}

	@Test
	public void testSetTerminus() {
		assertFalse("Station.setTerminus went wrong", stationTest2.isTerminus(0));
		
		stationTest2.setTerminus(0, true);
		
		assertTrue("Station.setTerminus went wrong", stationTest2.isTerminus(0));
	}

}
