package line_management;

import java.util.List;

public class Line {
	private List<Station> stations;
	private List<Canton> cantons;
	
	public Line() {
		
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
