package line_management;

/*
 * represents a passenger on the line
 */
public class Passenger {
	private long id;
	private int type;
	private int way;
	
	public Passenger(int type, long id, int way) {
		this.type = type;
		this.id = id;
		this.way = way;
	}

	/*
	 * returns the passenger's id
	 */
	public long getId() {
		return id;
	}
	
	/*
	 * returns the passenger's type
	 */
	public int getType() {
		return type;
	}
	
	/*
	 * returns the passenger's way
	 */
	public int getWay() {
		return way;
	}
}
