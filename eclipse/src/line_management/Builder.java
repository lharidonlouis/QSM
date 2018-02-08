package line_management;

import java.lang.Math;

public class Builder {
	private Line line;
	private boolean built;
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 300;
	
	public Builder() {
		built = false;
	}
	
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
		}
		
		station = new Station("Station" + String.valueOf(i), 0, line, 50, line.getLength());
		line.addStation(station);
		
		built = true;
	}
	
	public Line getLine() {
		return line;
	}
	
	public boolean isBuilt() {
		return built;
	}
}
