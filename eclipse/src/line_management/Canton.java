package line_management;

import line_management.Canton;
import line_management.Train;

/**
 * half of a segment, can contain only one train at a time
 * 
 * @author bastien.ck
 * @see Segment
 */
public class Canton {
	/**
	 * @see Canton#isOccupied()
	 * @see Canton#setOccupiedTrue()
	 * @see Canton#setOccupiedFalse()
	 */
	private boolean occupied;
	/**
	 * @see Segment
	 * @see Canton#getSegment()
	 */
	private Segment segment;

	/**
	 * Creates a new canton, not occupied
	 * @param segment
	 * the segment that contains this canton
	 */
	public Canton(Segment segment) {
		occupied = false;
		this.segment = segment;
	}
	
	/**
	 * makes a new train enter the canton
	 * and wait if it is occupied until it is free
	 * then waits if the station following the canton is occupied until it is free
	 * then makes the train exit the station it is leaving
	 * and marks the canton as occupied
	 * 
	 * @param train
	 * the train to enter the canton
	 * @param followingstation
	 * the station after this canton on the line
	 */
	public synchronized void enter(Train train, Station followingstation) {
		if(occupied || followingstation.isTrackOccupied(train.getWay())) {
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
	
	/**
	 * sets the canton that a train is leaving as not occupied
	 * and the train's current canton as null
	 * 
	 * @param train
	 * the train to exit this canton
	 */
	public synchronized void exit(Train train) {
		setOccupiedFalse();
		train.setCurrentCanton(null);
	}
	
	/**
	 * notifies the train waiting to enter the canton
	 */
	public synchronized void wakeWaitingTrain() {
		notify();
	}
	
	/**
	 * @return the status of the canton
	 */
	public boolean isOccupied() {
		return occupied;
	}
	
	/**
	 * sets the canton as occupied
	 */
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	
	/**
	 * sets the canton as not occupied
	 */
	public void setOccupiedFalse() {
		this.occupied = false;
	}
	
	/**
	 * @return the segment containing this canton
	 */
	public Segment getSegment() {
		return segment;
	}
}