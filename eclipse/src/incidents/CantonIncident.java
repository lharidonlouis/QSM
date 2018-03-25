package incidents;

import line_management.Canton;
import line_management.Line;
import line_management.Station;
import line_management.Train;

public class CantonIncident extends Incident {
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
	
	private void activateNextStart() {
		int stationIndex = 0;
		boolean found;
		Station nextStation;
		
		found = false;
		if (way == 0) {
			nextStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getEndPoint() + 1);
			stationIndex = line.getIndexForStation(nextStation);
					
			while (stationIndex<line.getStations().size() && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					nextStart = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex++;
			}
		}
		else {
			nextStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getStartPoint() - 1);
			stationIndex = line.getIndexForStation(nextStation);
			
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
		Station prevStation;
		
		found = false;
		if (way == 0) {
			prevStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getStartPoint() - 1);
			stationIndex = line.getIndexForStation(prevStation);
					
			while (stationIndex>=0 && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					prevTerminus = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex--;
			}
		}
		else {
			prevStation = line.getStationAtPosition(line.getSegmentForCanton(canton).getEndPoint() + 1);
			stationIndex = line.getIndexForStation(prevStation);
			
			while (stationIndex<line.getStations().size() && !found) {
				if (line.getStations().get(stationIndex).isBackup()) {
					prevTerminus = line.getStations().get(stationIndex);
					found = true;
				}
				stationIndex--;
			}
		}
		
		prevTerminus.setTerminus(way, true);
	}

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