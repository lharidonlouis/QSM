package line_management;

import java.util.ArrayList;

public class Line {
	private int length;
	private int usedLength;
	private ArrayList<Station> stations;
	private ArrayList<Segment> segments;
	private int nbSegments;

	public Line(int length) {
		this.length = length;
		usedLength = 0;
		stations = new ArrayList<Station>();
		segments = new ArrayList<Segment>();
		nbSegments = 0;
	}
	
	public Canton getCantonAtPosition(int position, int way) {
		int i = 0;
		
		while(segments.get(i).getEndPoint() < position)
			i++;
		
		return segments.get(i).getCanton(way);
	}
	
	public Station getStationAtPosition(int position) {
		int i = 0;
		
		while(stations.get(i).getPosition() != position)
			i++;
		
		return stations.get(i);
	}
	

	public void addSegment(Segment segment) throws SizeExceededException {
		if(usedLength + segment.getLength() <= length) {
			usedLength += segment.getLength();
			nbSegments++;
			segments.add(segment);
		}
		else {
			throw new SizeExceededException();
		}
		/* Use this in Builder and add a new Segment */
		/* canton = new Canton(id, totalLenght - usedLength, usedLength);*/

	}
	
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	public int getNbSegments() {
		return nbSegments;
	}
	public void setNbSegments(int nbSegments) {
		this.nbSegments = nbSegments;
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
	public ArrayList<Station> getStations() {
		return stations;
	}
	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}
}
