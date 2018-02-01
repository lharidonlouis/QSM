package line_management;

import java.util.List;

public class Line {
	private int length;
	private int usedLength;
	private List<Station> stations;
	private List<Canton> cantons;

	public Line() {
		
	}
	
	public Canton getCantonAtPosition(int position) {
		Canton canton = new Canton();
		
		/* 
		 * Function to write
		 */
		
		return canton;
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
