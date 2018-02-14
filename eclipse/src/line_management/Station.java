package line_management;

import java.util.ArrayList;

/*
 * represents a train station
 */
public class Station {
	private long id;
	private String name;
	private int type;
	private Line line;
	private int capacity;
	private int position;
	private ArrayList<Passenger> passengers;
	
	/*
	 * creates a new station on the line at a given position
	 * with a new name and a given capacity and type
	 */
	public Station(String name, int type, Line line, int capacity, int position){
		this.name = name;
		this.type = type;
		this.line = line;
		this.capacity = capacity;
		this.position = position;
	}
	
	/*
	 * allows the passengers on board of the train
	 * of which type matches the station's type to get off the train
	 * and the waiting passengers in the station to get in the train
	 */
	public void pickPassengers(Train train) {
		int i;
		ArrayList<Passenger> trainpassengers = train.getPassengers();
		for (i = 0; i < trainpassengers.size(); i++) {
			if (trainpassengers.get(i).getType() == type) {
				trainpassengers.remove(i);
			}
		}
		while ((train.getPassengers().size() < train.getCapacity()) && (passengers.size() > 0)) {
			trainpassengers.add(passengers.get(passengers.size()));
			passengers.remove(passengers.size());
		}
	}
	
	/*
	 * returns the station's position
	 */
	public int getPosition() {
		return position;
	}
	
	/*
	 * returns the line
	 */
	public Line getLine() {
		return line;
	}
	
	/*
	 * returns the station's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/*
	 * returns the passengers in the station
	 */
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	/*
	 * returns the station's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * returns the type
	 */
	public int getType() {
		return type;
	}
	
	/*
	 * returns the station's id
	 */
	public long getId() {
		return id;
	}
}
