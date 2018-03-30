package line_management;

import java.util.ArrayList;


/**
 * represents a line with its list of stations and segments in between the stations
 * 
 * @author bastien.ck
 */
public class Line {
	/**
	 * length of the line
	 * 
	 * @see Line#getLength()
	 */
	private int length;
	/**
	 * @see Station
	 * @see Line#getStations()
	 * @see Line#addStation(Station)
	 * @see Line#getStationAtPosition(int)
	 */
	private ArrayList<Station> stations;
	/**
	 * @see Segment
	 * @see Line#addSegment(Segment)
	 * @see Line#getSegmentAtPosition(int)
	 * @see Line#getSegmentForCanton(Canton)
	 * @see Line#getSegments()
	 */
	private ArrayList<Segment> segments;
	/**
	 * @see Train
	 * @see Line#getTrains()
	 */
	private ArrayList<Train> trains;
	/**
	 * @see Line#getNbSegments()
	 * @see Line#addSegment(Segment)
	 */
	private int nbSegments;
	/**
	 * @see Line#getNbStations()
	 * @see Line#addStation(Station)
	 */
	private int nbStations;

	/**
	 * Creates a new line as a null length and no stations nor segments
	 */
	public Line() {
		length = 0;
		stations = new ArrayList<Station>();
		segments = new ArrayList<Segment>();
		trains = new ArrayList<Train>();
		nbSegments = 0;
		nbStations = 0;
	}
	
	/**
	 * @param position
	 * the checked position
	 * @return the segment at a given position on the line
	 */
	public Segment getSegmentAtPosition(int position) {
		int i = 0;
		while(segments.get(i).getEndPoint() < position)
			i++;
		return segments.get(i);
	}
	
	/**
	 * @param position
	 * the checked position
	 * @param way
	 * the checked way
	 * @return the canton for a given way at a given position on the line
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
	
	/**
	 * @param segment
	 * the segment to check
	 * @param position
	 * the position checked
	 * @return if a position belongs to a segment
	 */
	public boolean positionInSegment(Segment segment, int position) {
		return (position >= segment.getStartPoint() && position <= segment.getEndPoint());
	}
	
	/**
	 * @param position
	 * the checked position
	 * @return the station that a train is reaching at a given position with a given speed
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
	
	/**
	 * @param canton
	 * the checked canton
	 * @return the segment that a given canton belongs to
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
	
	/**
	 * adds a new segment to the line
	 * @param segment
	 * the segment to add to the line
	 */
	public void addSegment(Segment segment) {
		length += segment.getLength();
		nbSegments++;
		segments.add(segment);
	}
	
	/**
	 * adds a new station to the line
	 * @param station
	 * the station to add to the line
	 */
	public void addStation(Station station) {
		nbStations++;
		length++;
		stations.add(station);
	}
	
	/**
	 * @return the number of stations on the line
	 */
	public int getNbStations() {
		return nbStations;
	}
	
	/**
	 * @return the segments of the line
	 * @see ArrayList
	 */
	public ArrayList<Segment> getSegments() {
		return segments;
	}
	
	/**
	 * @return the number of segments in the line
	 */
	public int getNbSegments() {
		return nbSegments;
	}
	
	/**
	 * @return the length of the line
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @return a short description of the line
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

	/**
	 * @return the list of trains on the line
	 * @see ArrayList
	 */
	public ArrayList<Train> getTrains() {
		return trains;
	}
	
	/**
	 * @return the list of stations in the line
	 * @see ArrayList
	 */
	public ArrayList<Station> getStations(){
		return stations;
	}
}
