package line_management;

/*
 * Class used to build a line containing a given number of stations
 * returns the built line for simulation purposes
 */
public class Builder {
	private Line line;
	private boolean built;
	//private static final int MIN_LENGTH = 100;
	//private static final int MAX_LENGTH = 300;
	
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
