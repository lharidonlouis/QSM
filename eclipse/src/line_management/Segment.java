package line_management;

/**
 * represents a part of the line
 * with two cantons to represent both ways
 * 
 * @author bastien.ck
 * @see Canton
 */
public class Segment {
	/**
	 * unique id of the segment
	 * @see Segment#getId()
	 */
	private int id;
	/**
	 * tab containing the two cantons of the segment
	 * @see Canton
	 * @see Segment#getCanton(int)
	 */
	private Canton[] cantons;
	/**
	 * start point of the segment on the line
	 * @see Segment#getStartPoint()
	 */
	private int startPoint;
	/**
	 * length of the segment on the line
	 * @see Segment#getLength()
	 * @see Segment#getEndPoint()
	 */
	private int length;
	/**
	 * line that the segment belongs to
	 * @see Line
	 * @see Segment#getLine()
	 */
	private Line line;
	
	/**
	 * creates a new segment with two new cantons
	 * 
	 * @param line
	 * the line that the segment belongs to
	 * @param startPoint
	 * where the segment starts on the line
	 * @param length
	 * the length used by this segment on the line
	 * @param id
	 * unique id of the segment on the line
	 */
	public Segment(int startPoint, int length, Line line, int id) {
		cantons = new Canton[2];
		cantons[0] = new Canton(this);
		cantons[1] = new Canton(this);
		this.startPoint = startPoint;
		this.length = length;
		this.line = line;
		this.id = id;
	}

	/**
	 * @param way
	 * the checked way
	 * @return the right canton for a given way
	 */
	public Canton getCanton(int way) {
		return cantons[way];
	}
	
	/**
	 * @return the beginning of the segment
	 */
	public int getStartPoint() {
		return startPoint;
	}
	
	/**
	 * @return the end of the segment
	 */
	public int getEndPoint() {
		return startPoint + length - 1;
	}
	
	/**
	 * @return the length of the segment
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * @return the segment's id
	 */
	public int getId() {
		return id;
	}
}
