package incidents;

import line_management.Line;
import line_management.Train;
import line_management.TrainArrivedException;
import line_management.Canton;
import line_management.Station;

public class Incident {
	private Line line;
	private Canton canton;
	private Train blockedtrain;
	private Station station;
	private Station nextStart;
	private Station previousTerminus;
	private boolean active;
	private int typeincident;

	public Incident(Line line, Canton canton, int way) {
		typeincident = 0;
		active = true;
		this.line = line;
		this.canton = canton;
		blockedtrain = null;
		
		if (canton.isOccupied()) {
			Train train = getTrainOnCanton(canton);
			train.block();
			blockedtrain = train;
		}
		else {
			canton.setOccupiedTrue();
		}

		activateNextStart(way);
		activatePreviousTerminus(way);
	}
	
	public Incident(Line line, Station station, int way) {
		typeincident = 1;
		active = true;
		this.line = line;
		this.station = station;
		int cantonposition;

		blockedtrain = null;
		
		
		if (way == 0)
			cantonposition = station.getPosition() - 1;
		else cantonposition = station.getPosition() + 1;
		
		try {
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
		} catch (TrainArrivedException e) {}
		

		activateNextStart(way);
		activatePreviousTerminus(way);
	}
	
	private void activateNextStart(int way) {
		int stationIndex = 0;
		boolean found;
		Station nextStation;
		
		if (typeincident == 0) {
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
		else {
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
	}
	
	
	private void activatePreviousTerminus(int way) {
		
	}
	
	private Train getTrainOnCanton(Canton canton) {
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

	private Train getTrainInStation(Station station) {
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

	public Station getStation() {
		return station;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void terminate() {
		if (typeincident == 0) {
			if (blockedtrain != null) {
				
			}
		}
			
		if (typeincident == 1) {
			
		}
		
		active = false;
	}
	
	
}
