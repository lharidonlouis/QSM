package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import line_management.Canton;
import line_management.Line;
import line_management.Station;
import line_management.Train;

public class CantonTest {

	@Test
	public void testCanton() {
		Canton cantonTest = new Canton();
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
	}

	@Test
	public void testEnter() {
		Canton cantonTest = new Canton();
		Line lineTest = new Line();
		Station stationTest = new Station("TestA", 1, lineTest, 53, 8, 1);
		Train trainTest = new Train(-2, 0, stationTest, 3);
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
		cantonTest.enter(trainTest);
		assertSame("Canton Setting Failed", cantonTest, trainTest.getCurrentCanton());
		assertFalse("Free Station Failed", stationTest.isOccupied());
		assertTrue("Canton not set as Occupied", cantonTest.isOccupied());
	}
	
	@Test
	public void testExit() {
		Canton cantonTest = new Canton();
		Line lineTest = new Line();
		Station stationTest = new Station("TestB", 0, lineTest, 5, 2, 5);
		Train trainTest = new Train(5, 1, stationTest, 8);
		cantonTest.enter(trainTest);
		assertTrue("Enter failed", cantonTest.isOccupied());
		cantonTest.exit(trainTest);
		assertFalse("Exit failed", cantonTest.isOccupied());
		assertEquals("Free Train Canton failed", null, trainTest.getCurrentCanton());
	}

	@Test
	public void testIsOccupied() {
		Canton cantonTest = new Canton();
		cantonTest.setOccupiedTrue();
		assertTrue("IsOccupied bugged", cantonTest.isOccupied());
		cantonTest.setOccupiedFalse();
		assertFalse("IsOccupied bugged", cantonTest.isOccupied());
	}

	@Test
	public void testSetOccupiedTrue() {
		Canton cantonTest = new Canton();
		cantonTest.setOccupiedTrue();
		assertTrue("Canton still not occupied", cantonTest.isOccupied());
	}

	@Test
	public void testSetOccupiedFalse() {
		Canton cantonTest = new Canton();
		cantonTest.setOccupiedTrue();
		cantonTest.setOccupiedFalse();
		assertFalse("Canton still occupied", cantonTest.isOccupied());
	}

}
