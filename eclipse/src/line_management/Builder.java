package line_management;

import java.util.Random;

/**
 * Class used to build a line containing a given number of stations
 * 
 * @author bastien.ck
 */
public class Builder {
	/**
	 * @see Line
	 * @see Builder#getLine()
	 */
	private Line line;
	/**
	 * @see Builder#isBuilt()
	 */
	private boolean built;
	/**
	 * minimum length for a segment between two stations
	 */
	private static final int MIN_LENGTH = 100;
	/**
	 * maximum length for a segment between two stations
	 */
	private static final int MAX_LENGTH = 300;
	/**
	 * minimum capacity for a station
	 */
	private static final int MIN_CAPACITY = 100;
	/**
	 * maximum capacity for a station
	 */
	private static final int MAX_CAPACITY = 500;
	
	/**
	 * Creates a new builder, sets built false
	 * @see Line#Line()
	 */
	public Builder() {
		line = new Line();
		built = false;
	}
	
	/**
	 * Main method
	 * Adds the stations with segments of length in range (min_length, max_length) in between
	 * then sets built as true
	 * 
	 * @param lineNbStations
	 * number of stations on the line
	 */
	public void build(int lineNbStations) {
		int i, type, capacity, length;
		Segment segment;
		boolean backup = true;
		Random r = new Random();
		
		int maxi = lineNbStations/5 + 1;
		int remaining = maxi;
		
		for (i = 0; i < lineNbStations - 1; i++) {
			boolean terminus[] = new boolean[2];
			boolean start[] = new boolean[2];
			
			type = r.nextInt(3);
			capacity = (int)(Math.random() * ((MAX_CAPACITY - MIN_CAPACITY) + 1)) + MIN_CAPACITY;
			
			if (i == 0) {
				terminus[0] = false;
				terminus[1] = true;
				start[0] = true;
				start[1] = false;
			}
			else if (i > 0) {
				terminus[1] = false;
				start[0] = false;
			}
			
			if (remaining == 0) {
				backup = true;
				remaining = maxi;
			}
			
			Station station = new Station("Station" + i, type, line, capacity, line.getLength(), i, backup, terminus, start);
			line.addStation(station);
			
			if (!backup)
				remaining--;
			backup = false;
			
			length = (int)(Math.random() * ((MAX_LENGTH - MIN_LENGTH) + 1)) + MIN_LENGTH;
			segment = new Segment(line.getLength(), length, line, i);
			line.addSegment(segment);
		}

		boolean terminus[] = {true, false};
		boolean start[] = {false, true};
		
		backup = true;
		
		type = (int)(Math.random() * ((2 - 0) + 1));
		capacity = (int)(Math.random() * ((MAX_CAPACITY - MIN_CAPACITY) + 1)) + MIN_CAPACITY;
		Station station = new Station("Station" + i, type, line, capacity, line.getLength(), i, true, terminus, start);
		line.addStation(station);
		
		built = true;
	}
	
	/**
	 * @return the built line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * @return the value of the built indicator
	 */
	public boolean isBuilt() {
		return built;
	}
}
