package incidents;

import line_management.Line;
import line_management.Train;
import line_management.Canton;
import line_management.Station;

/**
  * abstract class representing an incident
  * 
  * @author bastien.ck
  */
public abstract class Incident {
	/**
	 * @see Line
	 * @see Incident#getLine()
	 */
	protected Line line;
	/**
	 * @see Canton
	 * @see Incident#getCanton()
	 */
	protected Canton canton;
	/**
	 * @see Train
	 */
	protected Train blockedtrain;
	/**
	 * @see Station
	 */
	protected Station nextStart;
	/**
	 * @see Station
	 */
	protected Station prevTerminus;
	/**
	 * @see Incident#isActive()
	 */
	protected boolean active;
	protected int way;

	/**
	 * creates a new incident on a given way on the line and sets it active
	 * @param line
	 * the line impacted by the incident
	 * @param way
	 * the way on the line impacted by the incident
	 */
	public Incident(Line line, int way) {
		active = true;
		this.line = line;
		this.way = way;
		blockedtrain = null;
	}
	
	/**
	 * abstract method used to activate the next station with backup tracks on the line as a start
	 */
	protected abstract void activateNextStart();
	
	/**
	 * abstract method used to activate the previous station with backup tracks on the line as a terminus
	 */
	protected abstract void activatePreviousTerminus();
	
	/**
	 * deactivates the next station on the line using backup tracks to be used as a temporary start
	 */
	protected void deactivateNextStart() {
		nextStart.setStart(way, false);
	}

	/**
	 * deactivates the next station on the line using backup tracks to be used as a temporary terminus
	 */
	protected void deactivatePreviousTerminus() {
		prevTerminus.setTerminus(way, false);
	}
	
	/**
	 * @param canton
	 * the canton for which we want the occupying train
	 * @return the occupying train or null
	 */
	protected Train getTrainOnCanton(Canton canton) {
		for (int i = 0; i < line.getTrains().size(); i++) {
			if (line.getTrains().get(i).getCurrentCanton() == canton) {
				return line.getTrains().get(i);
			}
		}
		System.err.println("getTrainOnCanton returns null");
		
		return null;
	}

	/**
	 * @param station
	 * the station for which we want the occupying train
	 * @return the occupying train or null
	 */
	protected Train getTrainInStation(Station station) {
		for (int i = 0; i < line.getTrains().size(); i++) {
			if (line.getTrains().get(i).getCurrentStation() == station) {
				return line.getTrains().get(i);
			}
		}
		System.err.println("getTrainInStation returns null");
		
		return null;
	}
	
	/**
	 * @return the line on which the incident occurs
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @return the canton impacted by the incident
	 */
	public Canton getCanton() {
		return canton;
	}

	/**
	 * @return if the incident is still active or not
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * abstract method used to end an incident
	 */
	public abstract void terminate();
}
