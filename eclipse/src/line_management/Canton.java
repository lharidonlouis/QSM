package line_management;

public class Canton {
	private boolean occupied;

	public Canton() {
		occupied = false;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	public void setOccupiedFalse() {
		this.occupied = false;
	}
}