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
	 * then sets the old station of the train as not occupied
	 * and the canton as occupied
	 */
	public synchronized void enter(Train train, Station followingstation) {
		if(occupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		if(followingstation.isTrackOccupied(train.getWay())) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Station oldstation = train.getCurrentStation();
		train.setCurrentCanton(this);
		oldstation.exit(train);	
		setOccupiedTrue();
	}
	
	/*
	 * sets the canton that a train is leaving as not occupied
	 * and the train's current canton as null
	 */
	public synchronized void exit(Train train) {
		setOccupiedFalse();
		train.setCurrentCanton(null);
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