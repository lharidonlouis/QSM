package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import line_management.Builder;
import line_management.Canton;
import line_management.Line;
import line_management.Segment;
import line_management.Station;
import line_management.Train;

public class CantonTest {
	private int capacity = 0, id = 0, way = 0, speed = 3;
	
	private Line lineTest = new Line();
	
	private Segment segmentTest = new Segment(0, 5, lineTest, 0);
	
	private Canton cantonTest = new Canton(segmentTest);
	
	private Builder builderTest = new Builder();
	
	private Train trainTest;
	
	private Station stationTest;
	
	private Station stationTest2;
	
	@Before
	public void builderRunner() {
		builderTest.build(7);
		
		stationTest = builderTest.getLine().getStationAtPosition(0);
		stationTest2 = builderTest.getLine().getStationAtPosition(builderTest.getLine().getLength()-1);
		
		trainTest = new Train(id, way, stationTest, speed, capacity);
	}
	
	@Test
	public void testCanton() {
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
	}

	@Test
	public void testEnter() {
		assertFalse("Canton built as occupied", cantonTest.isOccupied());
		
		cantonTest.enter(trainTest, stationTest2);
		assertSame("Canton Setting Failed", cantonTest, trainTest.getCurrentCanton());
		assertFalse("Free Station Failed", stationTest.isTrackOccupied(trainTest.getWay()));
		assertTrue("Canton not set as Occupied", cantonTest.isOccupied());
	}
	
	@Test
	public void testExit() {
		cantonTest.enter(trainTest, stationTest2);
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
	
	@Test
	public void testGetSegment() {
		assertEquals("Canton.getSegment went wrong", segmentTest, cantonTest.getSegment());
	}

}
