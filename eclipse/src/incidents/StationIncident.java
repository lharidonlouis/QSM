package incidents;

import line_management.Line;
import line_management.Station;
import line_management.Train;

public class StationIncident extends Incident {
	private Station station;
	
	public StationIncident(Line line, Station station, int way) {
		super(line, way);
		
		this.station = station;
		int cantonposition;
		
		if (way == 0)
			cantonposition = station.getPosition() - 1;
		else cantonposition = station.getPosition() + 1;
		
		canton = line.getCantonAtPosition(cantonposition, way);
		
		if (station.isTrackOccupied(way)) {
			Train train = getTrainInStation(station);
			train.block();
			blockedtrain = train;
		}
		else {
			if (canton.isOccupied()) {
				Train train = getTrainOnCanton(canton);
				train.block();
				blockedtrain = train;
			}
			else
				station.setTrackOccupiedTrue(way);
		}
		
		activateNextStart();
		activatePreviousTerminus();
	}
	
	private void activateNextStart() {
		int stationIndex = 0;
		boolean found;
		
		stationIndex = line.getIndexForStation(station);
		found = false;
		if (way == 0) {
			stationIndex++;
			while (stationIndex<line.getStations().size() && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					nextStart = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex++;
			}
		}
		else {
			stationIndex--;
			while (stationIndex>=0 && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					nextStart = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex--;
			}
		}
		nextStart.setStart(way, true);
	}
	
	private void activatePreviousTerminus() {
		int stationIndex = 0;
		boolean found;
		
		stationIndex = line.getIndexForStation(station);
		found = false;
		if (way == 0) {
			stationIndex--;
			while (stationIndex>=0 && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					nextStart = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex--;
			}
		}
		else {
			stationIndex++;
			while (stationIndex<line.getStations().size() && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					nextStart = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex++;
			}
		}
		prevTerminus.setTerminus(way, true);
	}

	public Station getStation() {
		return station;
	}

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