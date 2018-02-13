package line_management;

import line_management.Canton;
import line_management.Train;

/*
 * half of a segment, can contain only one train at a time
 */
public class Canton {
	private boolean occupied;

	/*
	 * constructor simply sets the canton as not occupied
	 */
	public Canton() {
		occupied = false;
	}
	
	/*
	 * makes a new train enter the canton
	 * and wait if it is occupied until it is free
	 * then sets the old canton of the train as not occupied
	 * and the new one as occupied
	 */
	public synchronized void enter(Train train) {
		if (occupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Canton oldCanton = train.getCurrentCanton();
		train.setCurrentCanton(this);
		train.updatePosition();

		oldCanton.exit();
		setOccupiedTrue();
	}
	
	/*
	 * sets the canton that a train is leaving as not occupied
	 * and notifies the calling canton that the train can now enter it
	 */
	public synchronized void exit() {
		setOccupiedFalse();
		notify();
	}
	
	/*
	 * allows to check if the canton is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	/*
	 * sets the canton as occupied
	 */
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	/*
	 * sets the canton as not occupied
	 */
	public void setOccupiedFalse() {
		this.occupied = false;
	}
}