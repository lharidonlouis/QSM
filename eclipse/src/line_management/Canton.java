package line_management;

public class Canton {
	private boolean occupied;

	public Canton() {
		occupied = false;
	}
	
	public synchronized void enter(Train train) {
		if(occupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Canton oldcanton = train.getCurrentCanton();
		train.setCurrentCanton(this);
		train.updatePosition();
		oldcanton.exit();
		setOccupiedFalse();
	}
	
	public synchronized void exit() {
		setOccupiedFalse();
		notify();
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