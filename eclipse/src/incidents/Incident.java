package incidents;

import java.util.ArrayList;

import line_management.*;

public class Incident {
	private Line line;
	private ArrayList<Canton> cantons = new ArrayList<Canton>();
	private Station station;
	private boolean active;

	public Incident(Line line, Canton canton) {
		active = true;
		this.line = line;
		cantons.add(canton);
		
		if (canton.isOccupied()) {
			
		}
		else {
			
		}
	}
	
	public Incident(Line line, Station station, Canton canton1, Canton canton2, int way) {
		active = true;
		this.line = line;
		this.station = station;
		cantons.add(canton1);
		cantons.add(canton2);
		
		if (station.isTrackOccupied(way)) {
			
		}
		else {
			
		}
	}

	public Line getLine() {
		return line;
	}

	public ArrayList<Canton> getCantons() {
		return cantons;
	}

	public Station getStation() {
		return station;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void terminate() {
		active = false;
	}
}
