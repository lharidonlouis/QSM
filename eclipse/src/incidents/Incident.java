package incidents;

import java.util.ArrayList;

import line_management.*;

public class Incident {
	private Line line;
	private Canton canton;
	private Train blockedtrain;
	private Station station;
	private boolean active;
	private int typeincident;

	public Incident(Line line, Canton canton) {
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
		
		// Activer premier départ suivant
		// Activer premier terminus précédent
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
		

		// Activer premier départ suivant
		// Activer premier terminus précédent
	}
	
	private void activateNextStart() {
		
	}
	
	private void activatePreviousTerminus() {
		
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
		if (typeincident == 0)
		// parcourir trains
		// si aucun train bloqué sur le canton : le remettre non occupé
			
		if (typeincident == 1)
		// 
		
		active = false;
	}
	
	
}
