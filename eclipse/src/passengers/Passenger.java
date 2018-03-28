package passengers;

/**
 * represents a passenger on the line
 * 
 * @author bastien.ck
 */
public class Passenger {
	/**
	 * unique id of the passenger
	 * @see Passenger#getId()
	 */
	private long id;
	/**
	 * type of the passenger
	 * @see Passenger#getType()
	 */
	private int type;
	/**
	 * way of the passenger on the line
	 * @see Passenger#getWay()
	 */
	private int way;
	
	/**
	 * Creates a new passenger
	 * @param type
	 * @param id
	 * @param way
	 */
	public Passenger(int type, long id, int way) {
		this.type = type;
		this.id = id;
		this.way = way;
	}

	/**
	 * @return the passenger's id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the passenger's type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @return the passenger's way
	 */
	public int getWay() {
		return way;
	}
}
