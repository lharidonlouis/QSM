package line_management;

/*
 * represents a part of the line
 * with two cantons to represent both ways
 */
public class Segment {
	private long id;
	private Canton[] cantons;
	private int startPoint;
	private int length;
	private Line line;
	
	/*
	 * creates two new cantons
	 */
	public Segment(int startPoint, int length, Line line, long id) {
		cantons = new Canton[2];
		cantons[0] = new Canton();
		cantons[1] = new Canton();
		this.startPoint = startPoint;
		this.length = length;
		this.line = line;
		this.id = id;
	}

	/*
	 * returns the right canton for a given way
	 */
	public Canton getCanton(int way) {
		return cantons[way];
	}
	
	/*
	 * returns the beginning of the segment
	 */
	public int getStartPoint() {
		return startPoint;
	}
	
	/*
	 * returns the end of the segment
	 */
	public int getEndPoint() {
		return startPoint + length;
	}
	
	/*
	 * returns the length of the segment
	 */
	public int getLength() {
		return length;
	}
	
	/*
	 * returns the line
	 */
	public Line getLine() {
		return line;
	}
	
	/*
	 * returns the segment's id
	 */
	public long getId() {
		return id;
	}
}
