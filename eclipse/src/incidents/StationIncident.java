package incidents;

import line_management.Line;
import line_management.Station;
import line_management.Train;

/**
 * class instantiated in case of an incident in a station
 * 
 * @author bastien.ck
 * 
 * @see Incident
 */
public class StationIncident extends Incident {
	/**
	 * @see Station
	 * @see StationIncident#getStation()
	 */
	private Station station;
	
	/**
	 * creates a new incident in the station
	 * 
	 * @param line
	 * the line impacted by the incident
	 * @param station
	 * the station impacted by the incident
	 * @param way
	 * the way on the line impacted by the incident
	 * @see Incident#Incident(Line, int)
	 */
	public StationIncident(Line line, Station station, int way) {
		super(line, way);
		
		this.station = station;
		int cantonposition;
		
		if (way == 0)
			cantonposition = station.getPosition() - 1;
		else cantonposition = station.getPosition() + 1;
		
		canton = line.getCantonAtPosition(cantonposition, way);
		
		if (station.getTrainOnTrack(way) != null) {
			Train train = station.getTrainOnTrack(way);
			train.block();
			blockedtrain = train;
		}
		else {
			if (canton.getOccupyingTrain() != null) {
				Train train = canton.getOccupyingTrain();
				train.block();
				blockedtrain = train;
			}
			else
				station.block(way);
		}
		
		activateNextStart();
		activatePreviousTerminus();
	}
	
	/**
	 * gets the next station with backup tracks to set it as a temporary start on the line
	 */
	protected void activateNextStart() {
		int stationId = 0;
		boolean found;
		
		stationId = station.getId();
		found = false;
		if (way == 0) {
			stationId++;
			while (stationId<line.getStations().size() && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId++;
			}
		}
		else {
			stationId--;
			while (stationId>=0 && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId--;
			}
		}
		if (nextStart != null)
			nextStart.setStart(way, true);
	}
	
	/**
	 * gets the previous station with backup tracks to set it as a temporary terminus on the line
	 */
	protected void activatePreviousTerminus() {
		int stationId = 0;
		boolean found;
		
		stationId = station.getId();
		found = false;
		if (way == 0) {
			stationId--;
			while (stationId>=0 && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId--;
			}
		}
		else {
			stationId++;
			while (stationId<line.getStations().size() && !found) {
				if (line.getStations().get(stationId).isBackup()) {
					nextStart = line.getStations().get(stationId);
					found = true;
				}
				stationId++;
			}
		}
		if (prevTerminus != null)
			prevTerminus.setTerminus(way, true);
	}

	/**
	 * @return the station impacted by the incident
	 */
	public Station getStation() {
		return station;
	}

	/**
	 * ends the incident
	 */
	public void terminate() {
		if (blockedtrain != null) {
			blockedtrain.unblock();
			blockedtrain = null;
		}
		else
			station.setTrackOccupiedFalse(way);
		
		deactivateNextStart();
		deactivatePreviousTerminus();
		
		active = false;
	}
}