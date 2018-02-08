package line_management;

import java.util.ArrayList;

public class Line {
	private int length;
	private ArrayList<Station> stations;
	private ArrayList<Segment> segments;
	private int nbSegments;
	private int nbStations;

	public Line() {
		length = 0;
		stations = new ArrayList<Station>();
		segments = new ArrayList<Segment>();
		nbSegments = 0;
		nbStations = 0;
	}
	
	public Segment getSegmentAtPosition(int position) {
		int i = 0;
		
		while(segments.get(i).getEndPoint() < position)
			i++;
		
		return segments.get(i);
	}
	
	public Canton getCantonAtPosition(int position, int way) throws TrainArrivedException{
		int i = 0;
		
		while(segments.get(i).getEndPoint() < position)
			i++;
		
		if (i <= nbSegments)
			return segments.get(i).getCanton(way);
		
		else throw new TrainArrivedException();
		
	}
	
	public Station getStationAtPosition(int position) {
		int i = 0;
		
		while(stations.get(i).getPosition() != position)
			i++;
		
		return stations.get(i);
	}
	

	public void addSegment(Segment segment) {
		length += segment.getLength();
		nbSegments++;
		segments.add(segment);
	}
	
	public void addStation(Station station) {
		nbStations++;
		stations.add(station);
	}
	
	public int getNbStations() {
		return nbStations;
	}
	public void setNbStations(int nbStations) {
		this.nbStations = nbStations;
	}
	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
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
	public ArrayList<Station> getStations() {
		return stations;
	}
	public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}
}
