package line_management;

import java.util.List;

public class Line {
	private int length;
	private int usedLength;
	private List<Station> stations;
	private List<Canton> cantons;
	private int nbCantons;

	public Line() {
		/* Add constructor */
	}
	
	public Canton getCantonAtPosition(int position) {
		int i = 0;
		
		while (cantons.get(i).getEndPoint() < position)
			i++;
		
		return cantons.get(i);
	}
	
	public Station getStationAtPosition(int position) {
		int i = 0;
		
		while (stations.get(i).getPosition() != position)
			i++;
		
		return stations.get(i);
	}
	
	public int getNbCantons() {
		return nbCantons;
	}
	public void setNbCantons(int nbCantons) {
		this.nbCantons = nbCantons;
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
