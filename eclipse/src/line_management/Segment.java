package line_management;

/*
 * represents a part of the line
 * with two cantons to represent both ways
 */
public class Segment {
	private int id;
	private Canton[] cantons;
	private int startPoint;
	private int length;
	private Line line;
	
	/*
	 * creates two new cantons
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
		return startPoint + length - 1;
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
	public int getId() {
		return id;
	}
}
