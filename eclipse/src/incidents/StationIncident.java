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
		System.err.println("New incident at station " + station.getId() + ", way " + way);
		
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

		if (nextStart != null) {
			System.err.println("station " + nextStart.getId() + " is now a start for way " + way);
			nextStart.setStart(way, true);
		}
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

		if (prevTerminus != null) {
			System.err.println("station " + prevTerminus.getId() + " is now a terminus for way " + way);
			prevTerminus.setTerminus(way, true);
		}
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
			System.err.println("Unblocking blocked train in station " + station.getId());
			blockedtrain.unblock();
			canton.wakeWaitingTrain();
			station.wakeWaitingTrain();
			blockedtrain = null;
		}
		else {
			station.unblock(way);
			station.wakeWaitingTrain();
		}
		
		deactivateNextStart();
		deactivatePreviousTerminus();
		
		active = false;
	}
}
