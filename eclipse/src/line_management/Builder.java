package line_management;

/*
 * Class used to build a line containing a given number of stations
 * returns the built line for simulation purposes
 */
public class Builder {
	private boolean debug;
	private Line line;
	private boolean built;
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 300;
	private static final int MIN_CAPACITY = 100;
	private static final int MAX_CAPACITY = 500;
	
	/*
	 * Constructor simply sets built as false
	 */
	public Builder(boolean debug) {
		this.debug = debug;
		line = new Line();
		built = false;
	}
	
	/*
	 * Adds the stations with segments of length in range (min_length, max_length) in between
	 * then sets built as true
	 */
	public void build(int lineNbStations) {
		int i, type, capacity, length;
		Station station;
		Segment segment;
		if(debug) {
			System.out.println("Building...");
			System.out.println("Line length : " + line.getLength());
			System.out.println("Stations : " + line.getNbStations());
			System.out.println("Segments : " + line.getNbSegments());
		}
		
		for (i = 0; i < lineNbStations - 1; i++) {
			type = (int)(Math.random() * ((2 - 0) + 1));
			capacity = (int)(Math.random() * ((MAX_CAPACITY - MIN_CAPACITY) + 1)) + MIN_CAPACITY;
			station = new Station("Station" + i, type, line, capacity, line.getLength(), i);
			line.addStation(station);
			
			length = (int)(Math.random() * ((MAX_LENGTH - MIN_LENGTH) + 1)) + MIN_LENGTH;
			segment = new Segment(line.getLength(), length, line, i);
			line.addSegment(segment);
			
			if(debug) {
				System.out.println();
				System.out.println(station.getName() + " added : \n\tid : " + station.getId() + "\n\tposition : " + station.getPosition() +
				"\n\ttype : " + station.getType() + "\n\tcapacity : " + station.getCapacity());
				System.out.println("Segment added : \n\tstart : " + segment.getStartPoint() + "\n\tend : " + segment.getEndPoint() +
				"\n\tlength : " + segment.getLength() +	"\n\tid : " + segment.getId());

				System.out.println(line.getDescription());
			}
		}
		
		type = (int)(Math.random() * ((2 - 0) + 1));
		capacity = (int)(Math.random() * ((MAX_CAPACITY - MIN_CAPACITY) + 1)) + MIN_CAPACITY;
		station = new Station("Station" + i, type, line, capacity, line.getLength(), i);
		line.addStation(station);
		
		if(debug) {
			System.out.println();
			System.out.println(station.getName() + " added : \n" + station.getDescription());
			
			System.out.println(line.getDescription());
		}
		
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
