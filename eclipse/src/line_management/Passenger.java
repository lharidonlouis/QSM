package line_management;

/*
 * represents a passenger on the line
 */
public class Passenger {
	private long id;
	private int type;
	
	public Passenger(int type, long id) {
		this.type = type;
		this.id = id;
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
}
