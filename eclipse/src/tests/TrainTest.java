package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import line_management.Builder;
import line_management.Canton;
import line_management.Line;
import line_management.Passenger;
import line_management.Station;
import line_management.Train;

public class TrainTest {

	@Test
	public void testRun() {
		int type = 0, capacity = 0, position = 12, id = 0, way = 0, speed = 3;
		
		Builder builderTest = new Builder(true);

		builderTest.build(7);
		
		Train trainTest = new Train(id, way, builderTest.getLine().getStationAtPosition(0), speed, capacity);
		
		trainTest.run();
		
		assertEquals("Train run failed at some point", builderTest.getLine().getStationAtPosition(builderTest.getLine().getLength()-1), trainTest.getCurrentStation());
		
	}

	@Test
	public void testTrain() {
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		
		assertEquals("Failed to initiate line as Station's line", stationTest, trainTest.getCurrentStation());
		assertEquals("Failed to initiate current Canton as null", null, trainTest.getCurrentCanton());
		assertEquals("Failed to initiate position as station's position", position, trainTest.getPosition());
	}

	@Test
	public void testUpdatePosition() {
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		Train trainTest2 = new Train(2, 1, stationTest, 2, 1);
		
		assertEquals("Train: Failed to get Initiate position", stationTest.getPosition(), trainTest.getPosition());
		assertEquals("Train: Failed to get Initiate position", stationTest.getPosition(), trainTest2.getPosition());
		
		trainTest.updatePosition();
		assertEquals("Train: Failed to get new position", stationTest.getPosition()+1, trainTest.getPosition());
		
		trainTest2.updatePosition();
		assertEquals("Train: Failed to get new position", stationTest.getPosition()-1, trainTest2.getPosition());
	}

	@Test
	public void testGetCurrentCanton() {
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		
		cantonTest.enter(trainTest);
		assertEquals("Train: Canton Getter failed ", cantonTest, trainTest.getCurrentCanton());
		
		stationTest.enter(trainTest);
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
		int type = 0, capacity = 0, position = 12, id = 0;
		
		Line lineTest = new Line();
		Canton cantonTest = new Canton();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Station stationTest2 = new Station("StatTest2",1, lineTest, 8, 4, 3);
		Train trainTest = new Train(0, 0, stationTest, 3, 1);
		
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
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Way getter failed", way, trainTest.getWay());
	}

	@Test
	public void testGetPosition() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Position getter failed", position, trainTest.getPosition());
	}

	@Test
	public void testSetPosition() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Position getter failed", position, trainTest.getPosition());
		
		trainTest.setPosition(62);
		assertEquals("Position Setter failed", 62, trainTest.getPosition());
	}

	@Test
	public void testGetSpeed() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Speed getter failed", speed, trainTest.getSpeed());
	}

	@Test
	public void testSetSpeed() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Speed getter failed", speed, trainTest.getSpeed());
		
		trainTest.setSpeed(-8);
		assertEquals("Speed Setter failed", -8, trainTest.getSpeed());
	}

	@Test
	public void testGetCapacity() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, capacity);
		
		assertEquals("Capacity getter failed", capacity, trainTest.getCapacity());
	}

	@Test
	public void testGetPassengers() {
		/*
		 * Test Fail: NullPointerException on add(Passenger)
		 */
		int type = 0, capacity = 12, position = 12, id = 0;
		
		Line lineTest = new Line();
		Station stationTest = new Station("StatTest1",type, lineTest, capacity, position, id);
		Train trainTest = new Train(0, 0, stationTest, 3, capacity);
		
		Passenger trainPassengerTest1 = new Passenger(0, 0, 1);
		Passenger trainPassengerTest2 = new Passenger(0, 1, 0);
		Passenger trainPassengerTest3 = new Passenger(5, 2, 0);
		
		trainTest.getPassengers().add(trainPassengerTest1);
		trainTest.getPassengers().add(trainPassengerTest2);
		trainTest.getPassengers().add(trainPassengerTest3);
		
		assertEquals("Train Passengers missing", 3, trainTest.getPassengers().size());
	}

	@Test
	public void testGetLine() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Line getter failed", lineTest, trainTest.getLine());
	}

	@Test
	public void testGetId() {
		int id = 0, way = 1, speed = 5, type = 3, capacity = 42, position = 4;
		String name = "StatTest1";
		
		Line lineTest = new Line();
		Station stationTest = new Station(name, type, lineTest, capacity, position, id);
		Train trainTest = new Train(id, way, stationTest, speed, 1);
		
		assertEquals("Id getter failed", id, trainTest.getId());
	}

}
