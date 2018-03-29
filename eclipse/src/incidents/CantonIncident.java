package incidents;

import line_management.Canton;
import line_management.Line;
import line_management.Station;
import line_management.Train;

/**
  * Class instantiated in case of an incident on a canton
  * 
  * @author bastien.ck
  * 
  * @see Incident
  */
public class CantonIncident extends Incident {
	/**
	  * creates a new incident on the canton
	  * @param line
	  * the line impacted by the incident
	  * @param canton
	  * the canton impacted by the incident
	  * @param way
	  * the way on the line impacted by the incident
	  * @see Incident#Incident(Line, int)
	  */
	public CantonIncident(Line line, Canton canton, int way) {
		super(line,way);
		
		this.canton = canton;
		
		if (canton.isOccupied()) {
			Train train = getTrainOnCanton(canton);
			train.block();
			blockedtrain = train;
		}
		else {
			canton.setOccupiedTrue();
		}

		activateNextStart();
		activatePreviousTerminus();
	}
	
	/**
	  * gets the next station with backup tracks to set it as a temporary start on the line
	  */
	protected void activateNextStart() {
		int stationId = -1;
		boolean found;
		Station nextStation;
		
		found = false;
		if (way == 0) {
			nextStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getEndPoint() + 1);
			stationId = nextStation.getId();
					
			while (stationId<line.getStations().size() && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId++;
			}
		}
		else {
			nextStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getStartPoint() - 1);
			stationId = nextStation.getId();
			
			while (stationId>=0 && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId--;
			}
		}
		
		nextStart.setStart(way, true);
	}
	
	/**
	  * gets the previous station with backup tracks to set it as a temporary terminus on the line
	  */
	protected void activatePreviousTerminus() {
		int stationId = 0;
		boolean found;
		Station prevStation;
		
		found = false;
		if (way == 0) {
			prevStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getStartPoint() - 1);
			stationId = prevStation.getId();
					
			while (stationId>=0 && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					prevTerminus = line.getStations().get(stationId);
					found = true;
				}
				stationId--;
			}
		}
		else {
			prevStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getEndPoint() + 1);
			stationId = prevStation.getId();
			
			while (stationId<line.getStations().size() && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					prevTerminus = line.getStations().get(stationId);
					found = true;
				}
				stationId--;
			}
		}
		
		prevTerminus.setTerminus(way, true);
	}

	/**
	  * ends the incident
	  */
	public void terminate() {
		if (blockedtrain != null) {
			blockedtrain.unblock();
			blockedtrain = null;
		}
		else canton.setOccupiedFalse();
		
		deactivateNextStart();
		deactivatePreviousTerminus();
		
		active = false;
	}
}