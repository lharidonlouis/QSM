package line_management;

/*
 * Class used to build a line containing a given number of stations
 * returns the built line for simulation purposes
 */
public class Builder {
	private Line line;
	private boolean built;
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 300;
	private static final int MIN_CAPACITY = 100;
	private static final int MAX_CAPACITY = 500;
	
	/*
	 * Constructor simply sets built as false
	 */
	public Builder() {
		line = new Line();
		built = false;
	}
	
	/*
	 * Adds the stations with segments of length in range (min_length, max_length) in between
	 * then sets built as true
	 */
	public void build(int lineNbStations) {
		int i, type, capacity, length;
		Segment segment;
		boolean backup = true;
		
		int maxi = lineNbStations/5 + 1;
		int remaining = maxi;
		
		//System.out.println("Adding stations");
		for (i = 0; i < lineNbStations - 1; i++) {
			boolean terminus[] = new boolean[2];
			boolean start[] = new boolean[2];
			
			type = (int)(Math.random() * ((2 - 0) + 1));
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
