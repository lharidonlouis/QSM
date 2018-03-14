package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;
import line_management.Canton;
import line_management.Line;
import line_management.Segment;

public class SegmentTest extends TestCase{
	/*
	 * Initiate Segment and everything needed
	 */
	Line testLine = new Line();
	Segment testSegment = new Segment(0, 1, testLine, 0);
	Segment testSegment2 = new Segment(1, 3, testLine, 1);

	@Test
	public void testBuilder() throws Exception{
		/*
		 * Test getters for segment 1
		 */
		assertEquals(0, testSegment.getStartPoint());
		assertEquals(0, testSegment.getEndPoint());
		assertEquals(0, testSegment.getId());
		assertEquals(1, testSegment.getLength());
		assertSame(testLine, testSegment.getLine());
		/*
		 * Test getters for segment 2
		 */
		assertEquals(1, testSegment2.getStartPoint());
		assertEquals(3, testSegment2.getEndPoint());
		assertEquals(1, testSegment2.getId());
		assertEquals(3, testSegment2.getLength());
		assertSame(testLine, testSegment2.getLine());
		/*
		 * Test Canton getter
		 */
		assertEquals(false, testSegment.getCanton(0).isOccupied());
		/*
		 * Set Occupied as true and test Canton getter
		 */
		testSegment.getCanton(1).setOccupiedTrue();
		assertEquals(true, testSegment.getCanton(1).isOccupied());
		/*
		 * Set Occupied as false and test setter
		 */
	}

}
