package line_management;

import java.util.ArrayList;


/*
 * represents a line with its list of stations and segments in between the stations
 */
public class Line {
	private int length;
	private ArrayList<Station> stations;
	private ArrayList<Segment> segments;
	private ArrayList<Train> trains;
	private int nbSegments;
	private int nbStations;

	/*
	 * a new line as a null length and no stations nor segments
	 */
	public Line() {
		length = 0;
		stations = new ArrayList<Station>();
		segments = new ArrayList<Segment>();
		trains = new ArrayList<Train>();
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
	 */
	public Canton getCantonAtPosition(int position, int way) {
		int i=0;
		Segment segment = null;
		while(i < nbSegments && segment == null) {
			if (positionInSegment(segments.get(i), position))
				segment=segments.get(i);
			i++;
		}
		if (segment != null)
			return segment.getCanton(way);
		else
			return null;
	}
	
	/*
	 * allows to check if a position belongs to a segment
	 */
	public boolean positionInSegment(Segment segment, int position) {
		return (position >= segment.getStartPoint() && position <= segment.getEndPoint());
	}
	
	/*
	 * returns the station that a train is reaching at a given position with a given speed
	 */
	public Station getStationAtPosition(int position) {
		int i = 0;
		Station station = null;
		while(stations.get(i).getPosition() < position && i < nbStations) {
			i++;
		}
		if (stations.get(i).getPosition() == position)
			station = stations.get(i);
		
		
		return station;
	}
	
	/*
	 * returns the segment that a given canton belongs to
	 */
	public Segment getSegmentForCanton(Canton canton) {
		Segment segment = null;
		int i = 0, j;
		
		while (i < segments.size() && segment == null) {
			for (j = 0; j < 2; j++) {
				if (segments.get(i).getCanton(j) == canton)
					segment = segments.get(i);
			}
			i++;
		}
		
		return segment;
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
		String result = "Line length : " + length + "\nStations : " + nbStations + "\nSegments : " + nbSegments + "\n\n";
		for (Station station : stations) {
			result += "Station " + station.getId() + "\n\tTracks occupied : " + station.isTrackOccupied(0) +
					" / " + station.isTrackOccupied(1) + "\n";
			result += "\tCapacity : " + station.getCapacity() + "\n";
			result += "\tPosition : " + station.getPosition() + "\n";
			result += "\tIs start :\t" + station.isStart(0) + " / " + station.isStart(1) + "\n";
			result += "\tIs terminus :\t" + station.isTerminus(0) + " / " + station.isTerminus(1) + "\n";
			result += "\tIs backup : " + station.isBackup() + "\n\n";
		}
		return result;
	}

	/*
	 * returns the list of trains on the line
	 */
	public ArrayList<Train> getTrains() {
		return trains;
	}
	
	/*
	 * returns the list of stations in the line
	 */
	public ArrayList<Station> getStations(){
		return stations;
	}
}
