package line_management;

import java.util.List;

public class Line {
	private int length;
	private int usedLength;
	private List<Station> stations;
	private List<Canton> cantons;
	private int nbCantons;

	public int getNbCantons() {
		return nbCantons;
	}

	public void setNbCantons(int nbCantons) {
		this.nbCantons = nbCantons;
	}

	public Line() {
		
	}
	
	public Canton getCantonAtPosition(int position) {
		Canton canton = new Canton();
		
		/* 
		 * Function to write
		 */
		
		return canton;
	}
	
	public Station getStationAtPosition(int position) {
		Station station = new Station();
		
		/*
		 * Function to write
		 */
		
		return station;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getUsedLength() {
		return usedLength;
	}
	public void setUsedLength(int usedLength) {
		this.usedLength = usedLength;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public List<Canton> getCantons() {
		return cantons;
	}
	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
}
