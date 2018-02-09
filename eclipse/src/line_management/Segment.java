package line_management;

public class Segment {
	private long id;
	private Canton[] cantons;
	private int startPoint;
	private int length;
	private Line line;
	
	public Segment(int startPoint, int length, Line line, long id) {
		cantons = new Canton[2];
		cantons[0] = new Canton();
		cantons[1] = new Canton();
		this.startPoint = startPoint;
		this.length = length;
		this.line = line;
		this.id = id;
	}

	public Canton getCanton(int way) {
		return cantons[way];
	}
	public void setCantons(Canton[] cantons) {
		this.cantons = cantons;
	}
	public int getStartPoint() {
		return startPoint;
	}
	public int getEndPoint() {
		return startPoint + length;
	}
	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Canton[] getCantons() {
		return cantons;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
