package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import incidents.CantonIncident;
import line_management.Builder;
import line_management.Canton;
import line_management.Line;
import line_management.Station;
import line_management.Train;

public class CantonIncidentTest {
	private int capacity = 0, id = 0, way = 0, speed = 3;
	
	private Line lineTest;
	
	private Canton cantonTest;
	
	private Builder builderTest = new Builder();
	
	private Train trainTest;
	
	private Station stationTest;
	
	@Before
	public void builderRunner() {
		builderTest.build(7);
		
		lineTest = builderTest.getLine();
		
		stationTest = builderTest.getLine().getStationAtPosition(0);
		
		cantonTest = builderTest.getLine().getCantonAtPosition(1, 0);
		
		trainTest = new Train(id, way, stationTest, speed, capacity);
	}

	@Test
	public void testCantonIncident() {
		cantonTest.enter(trainTest, lineTest.getStationAtPosition(cantonTest.getSegment().getEndPoint()+1));
		CantonIncident cantonIncidentTest = new CantonIncident(lineTest, cantonTest, 0);
		
	}
	
	@Test
	public void testActivateNextStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testActivatePreviousTerminus() {
		fail("Not yet implemented");
	}

	@Test
	public void testTerminate() {
		fail("Not yet implemented");
	}

}
