package incidents;

import line_management.Line;
import line_management.Train;
import line_management.Canton;
import line_management.Station;

public class Incident {
	protected Line line;
	protected Canton canton;
	protected Train blockedtrain;
	protected Station nextStart;
	protected Station prevTerminus;
	protected boolean active;
	protected int way;

	public Incident(Line line, int way) {
		active = true;
		this.line = line;
		this.way = way;
		blockedtrain = null;
	}
	
	protected void deactivateNextStart() {
		nextStart.setStart(way, false);
	}
	
	protected void deactivatePreviousTerminus() {
		prevTerminus.setTerminus(way, false);
	}
	
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
	
	public Line getLine() {
		return line;
	}

	public Canton getCanton() {
		return canton;
	}

	public boolean isActive() {
		return active;
	}
}
