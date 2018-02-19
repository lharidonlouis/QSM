package line_management;

import java.util.ArrayList;
import line_management.TrainArrivedException;

/*
 * represents a line with its list of stations and segments in between the stations
 */
public class Line {
	private int length;
	private ArrayList<Station> stations;
	private ArrayList<Segment> segments;
	private int nbSegments;
	private int nbStations;

	/*
	 * a new line as a null length and no stations nor segments
	 */
	public Line() {
		length = 0;
		stations = new ArrayList<Station>();
		segments = new ArrayList<Segment>();
		nbSegments = 0;
		nbStations = 0;
	}
	
	/*
	 * returns the segment at a given position on the line
	 */
	public Segment getSegmentAtPosition(int position) {
		int i = 0;
		while(segments.get(i).getEndPoint() < position)
			i++;
		return segments.get(i);
	}
	
	/*
	 * returns the canton for a given way at a given position on the line
	 * 
	 * 
	 * 
	 * HANDLE SEGMENT INDEX -1
	 */
	public Canton getCantonAtPosition(int position, int way) throws TrainArrivedException {
		int i=0;
		while( (segments.get(i).getEndPoint() < position ) && (i < nbSegments) ) {
			i++;
		}
		if (i < nbSegments) {
			return segments.get(i).getCanton(way);
		}
		else throw new TrainArrivedException();
	}
	
	/*
	 * returns the station that a train is reaching at a given position with a given speed
	 * 
	 * NEEDS CONCEPTUAL REVIEW
	 */
	public Station getStationAtPosition(int position, int speed) {
		int i = 0;
		while( !(((position-speed) < stations.get(i).getPosition()) && (stations.get(i).getPosition() <  (position + speed)))) {
			i++;
		}
		return stations.get(i);
	}
	
	/*
	 * adds a new segment to the line
	 */
	public void addSegment(Segment segment) {
		length += segment.getLength();
		nbSegments++;
		segments.add(segment);
	}
	
	/*
	 * adds a new station to the line
	 */
	public void addStation(Station station) {
		nbStations++;
		length++;
		stations.add(station);
	}
	
	/*
	 * returns the number of stations
	 */
	public int getNbStations() {
		return nbStations;
	}
	
	/*
	 * returns the segments of the line
	 */
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	
	/*
	 * returns the number of segments
	 */
	public int getNbSegments() {
		return nbSegments;
	}
	
	/*
	 * returns the length of the line
	 */
	public int getLength() {
		return length;
	}
	
	/*
	 * returns a short description of the line
	 */
	public String getDescription() {
		return "Line length : " + length + "\nStations : " + nbStations + "\nSegments : " + nbSegments;
	}
}
