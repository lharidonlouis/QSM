package line_management;

import java.lang.Math;

/*
 * Class used to build a line containing a given number of stations
 * returns the built line for simulation purposes
 */
public class Builder {
	private Line line;
	private boolean built;
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 300;
	
	/*
	 * Constructor simply sets built as false
	 */
	public Builder() {
		built = false;
	}
	
	/*
	 * Adds the stations with segments of length in range (min_length, max_length) in between
	 * then sets built as true
	 */
	public void build(int lineNbStations) {
		line = new Line();
		long id = 0;
		Station station;
		Segment segment;
		int length;
		int i;
		
		for (i = 0; i < lineNbStations - 1; i++) {
			length = (int) (Math.random()*(MAX_LENGTH - MIN_LENGTH + 1)) + MIN_LENGTH;
			station = new Station("Station" + String.valueOf(i), 0, line, 50, line.getLength());
			line.addStation(station);
			segment = new Segment(line.getLength(), length , line, id);
			line.addSegment(segment);
			id++;
		}		
		station = new Station("Station" + String.valueOf(i), 0, line, 50, line.getLength());
		line.addStation(station);
		
		built = true;
	}
	
	/*
	 * returns the built line
	 */
	public Line getLine() {
		return line;
	}
	
	/*
	 * allows to check if the build is done
	 */
	public boolean isBuilt() {
		return built;
	}
}
