package line_management;

import java.util.ArrayList;
import line_management.TrainArrivedException;

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
		int i=0;
		while( (segments.get(i).getEndPoint() < position ) && (i < nbSegments) ) {
			i++;
		}
		//System.out.println("Position : " + position + " | Nb segments : " + nbSegments + " | Segment number " + i );
		if (i < nbSegments) {
			return segments.get(i).getCanton(way);
		}
		else throw new TrainArrivedException();
	}
	
	public Station getStationAtPosition(int position, int speed) {
		int i = 0;
		while( !(((position-speed) < stations.get(i).getPosition()) && (stations.get(i).getPosition() <  (position + speed)))) {
			//System.out.println("Sation " + i + " : " + (position-speed) + " < " + stations.get(i).getPosition() + " < " + (position+speed) + "????");
			i++;
		}
		//System.out.println("Sation " + i + " : " + (position-speed) + " < " + stations.get(i).getPosition() + " < " + (position+speed) + "!!!!");
		return stations.get(i);
	}
	

	public void addSegment(Segment segment) {
		length += segment.getLength();
		nbSegments++;
		segments.add(segment);
		System.out.println("Segment " + nbSegments + " added");
	}
	
	public void addStation(Station station) {
		nbStations++;
		stations.add(station);
		System.out.println("Station " + nbStations + " added");
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
