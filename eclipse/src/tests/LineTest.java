package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import line_management.Line;
import line_management.Segment;
import line_management.Station;
import line_management.TrainArrivedException;

public class LineTest {

	int startPoint1 = 4, startPoint2 = 5, position1 = 0, position2 = 5, length1 = 1, length2 = 4;
	
	private Line lineTest = new Line();
	
	private Segment segmentTest = new Segment(startPoint1, length1, lineTest, 0);
	private Segment segmentTest2 = new Segment(startPoint2, length2, lineTest, 5);
	
	private ArrayList<Segment> segments = new ArrayList<Segment>();
	
	private boolean terminus[] = {false, false}, start[] = {false, false}, backup = false;
	
	private Station stationTest = new Station("TestStat1", 0, lineTest, 42, position1, 1, backup, terminus, start);
	private Station stationTest2 = new Station("TestStat2", 1, lineTest, 55, position2, 4, backup, terminus, start);

	@Test
	public void testLine() {
		assertEquals("Wrong constructor length", 0, lineTest.getLength());
		assertEquals("Wrong number of segments in Line Constructor", 0, lineTest.getNbSegments());
		assertEquals("Wrong number of stations in Line Constructor", 0, lineTest.getNbStations());		
	}

	@Test
	public void testGetSegmentAtPosition() {
		lineTest.addSegment(segmentTest);
		lineTest.addSegment(segmentTest2);
		assertEquals("GetSegmentAtPosition test 1 went wrong", segmentTest, lineTest.getSegmentAtPosition(startPoint1));
		assertEquals("GetSegmentAtPosition test 2 went wrong", segmentTest2, lineTest.getSegmentAtPosition(startPoint2));
	}

	@Test
	public void testGetCantonAtPosition() throws TrainArrivedException{
		lineTest.addSegment(segmentTest);
		lineTest.addSegment(segmentTest2);
		assertEquals("getCantonAtPosition Failed", segmentTest.getCanton(1), lineTest.getCantonAtPosition(startPoint1, 1));
		assertEquals("getCantonAtPosition Failed", segmentTest2.getCanton(0), lineTest.getCantonAtPosition(startPoint2, 0));
	}

	@Test(expected=TrainArrivedException.class)
	public void testGetCantonAtPositionException() throws TrainArrivedException{
		lineTest.getCantonAtPosition(startPoint1, 1);
		}
	
	@Test
	public void testPositionInSegment() {
		assertTrue("PositionInSegment Failed", lineTest.positionInSegment(segmentTest, startPoint1));
		assertTrue("PositionInSegment Failed", lineTest.positionInSegment(segmentTest2, startPoint2));
	}

	@Test
	public void testGetStationAtPosition() {
		lineTest.addStation(stationTest);
		lineTest.addStation(stationTest2);
		assertEquals("GetStationAtPosition test 1 went wrong", stationTest, lineTest.getStationAtPosition(position1));
		assertEquals("GetStationAtPosition test 2 went wrong", stationTest2, lineTest.getStationAtPosition(position2));
	}

	@Test
	public void testAddSegment() {
		assertEquals("Failed to inititate nbSegment", 0, lineTest.getNbSegments());
		lineTest.addSegment(segmentTest);
		assertEquals("Add segment failed to up nbSegment", 1, lineTest.getNbSegments());
		assertEquals("Failed to add the good segment", segmentTest, lineTest.getSegmentAtPosition(position1));
		lineTest.addSegment(segmentTest2);
		assertEquals("Add segment failed to up nbSegment", 2, lineTest.getNbSegments());
		assertEquals("Failed to add the good segment", segmentTest2, lineTest.getSegmentAtPosition(position2));
	}

	@Test
	public void testAddStation() {
		assertEquals("Failed to inititate nbStation", 0, lineTest.getNbStations());
		lineTest.addStation(stationTest);
		assertEquals("Add station failed to up nbStation", 1, lineTest.getNbStations());
		assertEquals("Failed to add the good station", stationTest, lineTest.getStationAtPosition(position1));
		lineTest.addStation(stationTest2);
		assertEquals("Add station failed to up nbStation", 2, lineTest.getNbStations());
		assertEquals("Failed to add the good station", stationTest2, lineTest.getStationAtPosition(position2));
	}

	@Test
	public void testGetNbStations() {
		assertEquals("Line number of Station getter error", 0, lineTest.getNbStations());
		lineTest.addStation(stationTest);
		assertEquals("Line number of Station getter error", 1, lineTest.getNbStations());
	}

	@Test
	public void testGetSegments() {
		lineTest.addSegment(segmentTest);
		segments.add(segmentTest);
		assertEquals("Failed to get Stations Array", segments, lineTest.getSegments());
		lineTest.addSegment(segmentTest2);
		segments.add(segmentTest2);
		assertEquals("Failed to get Stations Array", segments, lineTest.getSegments());
	}

	@Test
	public void testGetNbSegments() {
		Line lineTest = new Line();
		Segment segmentTest = new Segment(0, 5, lineTest, 0);
		assertEquals("Line number of Segment getter error", 0, lineTest.getNbSegments());
		lineTest.addSegment(segmentTest);
		assertEquals("Line number of Segment getter error", 1, lineTest.getNbSegments());
	}

	@Test
	public void testGetLength() {
		assertEquals("Line length getter error", 0, lineTest.getLength());
		lineTest.addSegment(segmentTest);
		assertEquals("Line length getter error", length1, lineTest.getLength());
		lineTest.addStation(stationTest);
		assertEquals("Line length getter error", length1 + 1, lineTest.getLength());
	}

	@Test
	public void testGetDescription() {
		Line lineTest = new Line();
		assertEquals("Description went wrong"
				, "Line length : " + lineTest.getLength() + "\nStations : " + lineTest.getNbStations() + "\nSegments : " + lineTest.getNbSegments()
				, lineTest.getDescription());
	}

}
