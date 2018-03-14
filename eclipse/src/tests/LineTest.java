package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import line_management.Line;
import line_management.Segment;
import line_management.Station;
import line_management.TrainArrivedException;

public class LineTest {

	@Test
	public void testLine() {
		Line lineTest = new Line();
		assertEquals("Wrong constructor length", 0, lineTest.getLength());
		assertEquals("Wrong number of segments in Line Constructor", 0, lineTest.getNbSegments());
		assertEquals("Wrong number of stations in Line Constructor", 0, lineTest.getNbStations());		
	}

	@Test
	public void testGetSegmentAtPosition() {
		int startPoint1 = 2, startPoint2 = 9;
		Line lineTest = new Line();
		Segment segmentTest = new Segment(startPoint1, 4, lineTest, 0);
		Segment segmentTest2 = new Segment(startPoint2, 4, lineTest, 5);
		lineTest.addSegment(segmentTest);
		lineTest.addSegment(segmentTest2);
		assertEquals("GetSegmentAtPosition test 1 went wrong", segmentTest, lineTest.getSegmentAtPosition(startPoint1));
		assertEquals("GetSegmentAtPosition test 2 went wrong", segmentTest2, lineTest.getSegmentAtPosition(startPoint2));
	}

	@Test
	public void testGetCantonAtPosition() throws TrainArrivedException{
		int startPoint1 = 4, startPoint2 = 5;
		Line lineTest = new Line();
		Segment segmentTest = new Segment(startPoint1, 1, lineTest, 0);
		Segment segmentTest2 = new Segment(startPoint2, 4, lineTest, 5);
		lineTest.addSegment(segmentTest);
		lineTest.addSegment(segmentTest2);
		assertEquals("getCantonAtPosition Failed", segmentTest.getCanton(1), lineTest.getCantonAtPosition(startPoint1, 1));
		assertEquals("getCantonAtPosition Failed", segmentTest2.getCanton(0), lineTest.getCantonAtPosition(startPoint2, 0));
	}

	@Test(expected=TrainArrivedException.class)
	public void testGetCantonAtPositionException() throws TrainArrivedException{
		int startPoint1 = 4, startPoint2 = 5;
		Line lineTest = new Line();
		lineTest.getCantonAtPosition(startPoint1, 1);
		}
	
	@Test
	public void testPositionInSegment() {
		Line lineTest = new Line();
		Segment segmentTest = new Segment(0, 3, lineTest, 2);
		Segment segmentTest2 = new Segment(4, 5, lineTest, 5);
		assertTrue("PositionInSegment Failed", lineTest.positionInSegment(segmentTest, 0));
		assertTrue("PositionInSegment Failed", lineTest.positionInSegment(segmentTest2, 4));
	}

	@Test
	public void testGetStationAtPosition() {
		int position1 = 3, position2 = 5;
		Line lineTest = new Line();
		Station stationTest = new Station("TestStat1", 0, lineTest, 42, position1, 1);
		Station stationTest2 = new Station("TestStat2", 1, lineTest, 55, position2, 4);
		lineTest.addStation(stationTest);
		lineTest.addStation(stationTest2);
		assertEquals("GetStationAtPosition test 1 went wrong", stationTest, lineTest.getStationAtPosition(position1));
		assertEquals("GetStationAtPosition test 2 went wrong", stationTest2, lineTest.getStationAtPosition(position2));
	}

	@Test
	public void testAddSegment() {
		Line lineTest = new Line();
		Segment segmentTest = new Segment(0, 3, lineTest, 2);
		Segment segmentTest2 = new Segment(4, 5, lineTest, 5);
		assertEquals("Failed to inititate nbSegment", 0, lineTest.getNbSegments());
		lineTest.addSegment(segmentTest);
		assertEquals("Add segment failed to up nbSegment", 1, lineTest.getNbSegments());
		assertEquals("Failed to add the good segment", segmentTest, lineTest.getSegmentAtPosition(0));
		lineTest.addSegment(segmentTest2);
		assertEquals("Add segment failed to up nbSegment", 2, lineTest.getNbSegments());
		assertEquals("Failed to add the good segment", segmentTest2, lineTest.getSegmentAtPosition(4));
	}

	@Test
	public void testAddStation() {
		Line lineTest = new Line();
		Station stationTest = new Station("TestStat1", 0, lineTest, 42, 5, 1);
		Station stationTest2 = new Station("TestStat2", 1, lineTest, 55, 8, 4);
		assertEquals("Failed to inititate nbStation", 0, lineTest.getNbStations());
		lineTest.addStation(stationTest);
		assertEquals("Add station failed to up nbStation", 1, lineTest.getNbStations());
		assertEquals("Failed to add the good station", stationTest, lineTest.getStationAtPosition(5));
		lineTest.addStation(stationTest2);
		assertEquals("Add station failed to up nbStation", 2, lineTest.getNbStations());
		assertEquals("Failed to add the good station", stationTest2, lineTest.getStationAtPosition(8));
	}

	@Test
	public void testGetNbStations() {
		Line lineTest = new Line();
		Station stationTest = new Station("TestA", 5, lineTest, 22, 2, 4);
		assertEquals("Line number of Station getter error", 0, lineTest.getNbStations());
		lineTest.addStation(stationTest);
		assertEquals("Line number of Station getter error", 1, lineTest.getNbStations());
	}

	@Test
	public void testGetSegments() {
		Line lineTest = new Line();
		Segment segmentTest = new Segment(0, 5, lineTest, 0);
		Segment segmentTest2 = new Segment(6, 2, lineTest, 8);
		ArrayList<Segment> segments = new ArrayList<Segment>();
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
		Line lineTest = new Line();
		Segment segmentTest = new Segment(0, 5, lineTest, 0);
		Station stationTest = new Station("TestA", 5, lineTest, 22, 2, 4);
		assertEquals("Line length getter error", 0, lineTest.getLength());
		lineTest.addSegment(segmentTest);
		assertEquals("Line length getter error", 5, lineTest.getLength());
		lineTest.addStation(stationTest);
		assertEquals("Line length getter error", 6, lineTest.getLength());
	}

	@Test
	public void testGetDescription() {
		Line lineTest = new Line();
		assertEquals("Description went wrong"
				, "Line length : " + lineTest.getLength() + "\nStations : " + lineTest.getNbStations() + "\nSegments : " + lineTest.getNbSegments()
				, lineTest.getDescription());
	}

}
