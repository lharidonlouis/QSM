package incidents;

import line_management.Line;
import line_management.Train;
import line_management.Canton;
import line_management.Station;

/*
 * abstract class representing an incident
 */
public abstract class Incident {
	protected Line line;
	protected Canton canton;
	protected Train blockedtrain;
	protected Station nextStart;
	protected Station prevTerminus;
	protected boolean active;
	protected int way;

	/*
	 * creates a new incident on a given way on the line and sets it active
	 */
	public Incident(Line line, int way) {
		active = true;
		this.line = line;
		this.way = way;
		blockedtrain = null;
	}
	
	/*
	 * deactivates the next station on the line using backup tracks to be used as a temporary start
	 */
	protected void deactivateNextStart() {
		nextStart.setStart(way, false);
	}

	/*
	 * deactivates the next station on the line using backup tracks to be used as a temporary terminus
	 */
	protected void deactivatePreviousTerminus() {
		prevTerminus.setTerminus(way, false);
	}
	
	/*
	 * returns the train occupying a given canton or null if none is on the canton
	 */
	protected Train getTrainOnCanton(Canton canton) {
		Train train = null;
		boolean found = false;
		int i = 0;
		
		while (i < line.getTrains().size() && !found) {
			if (line.getTrains().get(i).getCurrentCanton() == canton) {
				train = line.getTrains().get(i);
				found = true;
			}
		}
		
		return train;
	}

	/*
	 * returns the train occupying a given station or null if none is in the station
	 */
	protected Train getTrainInStation(Station station) {
		Train train = null;
		boolean found = false;
		int i = 0;
		
		while (i < line.getTrains().size() && !found) {
			if (line.getTrains().get(i).getCurrentStation() == station) {
				train = line.getTrains().get(i);
				found = true;
			}
		}
		
		return train;
	}
	
	/*
	 * returns the line on which the incident occurs
	 */
	public Line getLine() {
		return line;
	}

	/*
	 * returns the canton impacted by the incident
	 */
	public Canton getCanton() {
		return canton;
	}

	/*
	 * allows to check if the incident is active
	 */
	public boolean isActive() {
		return active;
	}
}
