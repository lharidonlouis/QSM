package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import line_management.Canton;
import line_management.Line;
import line_management.Segment;
import line_management.Station;
import line_management.Train;

public class CantonTest {
	
	private boolean terminus[] = {false, false}, start[] = {false, false}, backup = false;

	private Line lineTest = new Line();
	
	private Segment segmentTest = new Segment(0, 5, lineTest, 0);
	
	private Canton cantonTest = new Canton(segmentTest);
	
	private Station stationTest = new Station("TestStat1", 2, lineTest, 22, 5, 2, backup, terminus, start);
	
	private Train trainTest = new Train(5, 1, stationTest, 8, 1);
	
	private Station nextStation = trainTest.getLine().getStationAtPosition(trainTest.getPosition()+segmentTest.getLength());
	
	
	@Test
	public void testCanton() {
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
	}

	@Test
	public void testEnter() {
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
		
		cantonTest.enter(trainTest, nextStation);
		assertSame("Canton Setting Failed", cantonTest, trainTest.getCurrentCanton());
		assertFalse("Free Station Failed", stationTest.isTrackOccupied(trainTest.getWay()));
		assertTrue("Canton not set as Occupied", cantonTest.isOccupied());
	}
	
	@Test
	public void testExit() {
		cantonTest.enter(trainTest, nextStation);
		assertTrue("Enter failed", cantonTest.isOccupied());
		
		cantonTest.exit(trainTest);
		assertFalse("Exit failed", cantonTest.isOccupied());
		assertEquals("Free Train Canton failed", null, trainTest.getCurrentCanton());
	}

	@Test
	public void testIsOccupied() {
		cantonTest.setOccupiedTrue();
		assertTrue("IsOccupied bugged", cantonTest.isOccupied());
		
		cantonTest.setOccupiedFalse();
		assertFalse("IsOccupied bugged", cantonTest.isOccupied());
	}

	@Test
	public void testSetOccupiedTrue() {
		cantonTest.setOccupiedTrue();
		
		assertTrue("Canton still not occupied", cantonTest.isOccupied());
	}

	@Test
	public void testSetOccupiedFalse() {
		cantonTest.setOccupiedTrue();
		cantonTest.setOccupiedFalse();
		
		assertFalse("Canton still occupied", cantonTest.isOccupied());
	}

}
