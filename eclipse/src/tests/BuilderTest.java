package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import line_management.Builder;
import line_management.Line;
import line_management.Station;

public class BuilderTest {
	
	private Builder builderTest = new Builder();
	
	private Line lineTest = new Line();
	
	private boolean terminus[] = {false, false}, start[] = {false, false}, backup = false;
	
	private Station stationTest = new Station("TestStat1", 2, lineTest, 22, 5, 2, backup, terminus, start);

	@Test
	public void testBuilder() {
		assertFalse("Builder Constructor Failed to init built", builderTest.isBuilt());

	}

	@Test
	public void testBuild() {
		int nbStationsTest = 4;
		assertEquals("Line created with stations", 0, builderTest.getLine().getNbStations());
		assertEquals("Line created with segments", 0, builderTest.getLine().getNbSegments());
		builderTest.build(nbStationsTest);
		assertEquals("Wrong number of stations built", nbStationsTest, builderTest.getLine().getNbStations());
		assertEquals("Wrong number of segments built", nbStationsTest-1, builderTest.getLine().getNbSegments());
		
	}

	@Test
	public void testGetLine() {
		assertEquals("GetLine from Builder Failed", 0, builderTest.getLine().getNbStations());
		builderTest.getLine().addStation(stationTest);
		assertEquals("GetLine from Builder Failed", 1, builderTest.getLine().getNbStations());
	}

	@Test
	public void testIsBuilt() {
		assertFalse("Builder Constructor Failed: Set as Built", builderTest.isBuilt());
		builderTest.build(3);
		assertTrue("Builder isBuilt Failed: Set as not Built", builderTest.isBuilt());
	}

}
