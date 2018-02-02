package line_management;

public class Canton {
	private long id;
	private int startPoint;
	private int length;
	private Line line;
	private boolean occupied;

	public Canton() {
		
	}
	
	public boolean getOccupied() {
		return occupied;
	}
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	public void setOccupiedFalse() {
		this.occupied = false;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStartPoint() {
		return startPoint;
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
}
