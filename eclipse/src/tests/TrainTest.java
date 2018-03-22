package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import line_management.Builder;
import line_management.Canton;
import line_management.Line;
import line_management.Passenger;
import line_management.Segment;
import line_management.Station;
import line_management.Train;
import line_management.TrainArrivedException;

public class TrainTest {
	private int capacity = 0, id = 0, way = 0, speed = 3;
	private int capacity2 = 3, id2 = 1, way2 = 1, speed2 = 1;
	
	private Line lineTest = new Line();

	private Segment segmentTest = new Segment(0, 5, lineTest, 0);
	
	private Canton cantonTest = new Canton(segmentTest);

	private Builder builderTest = new Builder(true);
	
	private Train trainTest;
	
	private Train trainTest2;
	
	private Station stationTest;
	
	private Station stationTest2;
	/*
	private Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id, backup, terminus, start);
	private Station stationTest2 = new Station("StatTest1",type, lineTest, capacity, position, id, backup, terminus, start);
	
	private Train trainTest = new Train(0, 0, stationTest, 3, 1);
	private Train trainTest2 = new Train(2, 1, stationTest, 2, 1);
	*/
	private Passenger trainPassengerTest1 = new Passenger(0, 0, 1);
	private Passenger trainPassengerTest2 = new Passenger(0, 1, 0);
	private Passenger trainPassengerTest3 = new Passenger(5, 2, 0);
	
	@Before
	public void builderRunner() {
		builderTest.build(7);
		
		stationTest = builderTest.getLine().getStationAtPosition(0);
		stationTest2 = builderTest.getLine().getStationAtPosition(builderTest.getLine().getLength()-1);
		
		trainTest = new Train(id, way, stationTest, speed, capacity);
		trainTest2 = new Train(id2, way2, stationTest2, speed2, capacity2);

	}
	
	@Test
	public void testRun(){
		System.out.println("-------------TEST RUN STARTS---------------");
		trainTest.run();
		
		assertEquals("Train run failed at some point (way=0)", builderTest.getLine().getStationAtPosition(builderTest.getLine().getLength()-1), trainTest.getCurrentStation());
		
		trainTest2.run();
		
		assertEquals("Train run failed at some point (way=1)", builderTest.getLine().getStationAtPosition(0), trainTest2.getCurrentStation());
		
		System.out.println("-------------TEST RUN ENDS---------------");
	}

	@Test
	public void testTrain() {
		assertEquals("Failed to initiate line as Station's line", stationTest, trainTest.getCurrentStation());
		assertEquals("Failed to initiate current Canton as null", null, trainTest.getCurrentCanton());
		assertEquals("Failed to initiate position as station's position", stationTest.getPosition(), trainTest.getPosition());
	}

	@Test
	public void testUpdatePosition() {
		assertEquals("Train: Failed to get Initiate position", stationTest.getPosition(), trainTest.getPosition());
		assertEquals("Train: Failed to get Initiate position", stationTest2.getPosition(), trainTest2.getPosition());
		
		trainTest.updatePosition();
		assertEquals("Train: Failed to get new position", stationTest.getPosition()+1, trainTest.getPosition());
		
		trainTest2.updatePosition();
		assertEquals("Train: Failed to get new position", stationTest2.getPosition()-1, trainTest2.getPosition());
	}

	@Test
	public void testGetCurrentCanton() {
		cantonTest.enter(trainTest, stationTest2);
		assertEquals("Train: Canton Getter failed ", cantonTest, trainTest.getCurrentCanton());
		
		stationTest2.enter(trainTest);
		assertEquals("Train: Canton Getter failed ", null, trainTest.getCurrentCanton());
	}

	@Test
	public void testSetCurrentCanton() {
		/*
		 * Would be useless since same as testGetCurrentStation
		 */
	}

	@Test
	public void testGetCurrentStation() {
		assertEquals("Train: failed to get initial station", stationTest, trainTest.getCurrentStation());
		
		trainTest.setCurrentStation(stationTest2);
		assertEquals("Train: failed to get current station", stationTest2, trainTest.getCurrentStation());
	}

	@Test
	public void testSetCurrentStation() {
		/*
		 * Would be useless since same as testGetCurrentStation
		 */
	}

	@Test
	public void testGetWay() {
		assertEquals("Way getter failed", way, trainTest.getWay());
	}

	@Test
	public void testGetPosition() {
		assertEquals("Position getter failed", stationTest.getPosition(), trainTest.getPosition());
	}

	@Test
	public void testSetPosition() {
		assertEquals("Position getter failed", stationTest.getPosition(), trainTest.getPosition());
		
		trainTest.setPosition(62);
		assertEquals("Position Setter failed", 62, trainTest.getPosition());
	}

	@Test
	public void testGetSpeed() {
		assertEquals("Speed getter failed", speed, trainTest.getSpeed());
	}

	@Test
	public void testSetSpeed() {
		assertEquals("Speed getter failed", speed, trainTest.getSpeed());
		
		trainTest.setSpeed(-8);
		assertEquals("Speed Setter failed", -8, trainTest.getSpeed());
	}

	@Test
	public void testGetCapacity() {
		assertEquals("Capacity getter failed", capacity, trainTest.getCapacity());
	}

	@Test
	public void testGetPassengers() {
		trainTest.getPassengers().add(trainPassengerTest1);
		trainTest.getPassengers().add(trainPassengerTest2);
		trainTest.getPassengers().add(trainPassengerTest3);
		
		assertEquals("Train Passengers missing", 3, trainTest.getPassengers().size());
	}

	@Test
	public void testGetLine() {
		assertEquals("Line getter failed", builderTest.getLine(), trainTest.getLine());
	}

	@Test
	public void testGetId() {
		assertEquals("Id getter failed", id, trainTest.getId());
	}

}
