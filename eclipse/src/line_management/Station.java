package line_management;

import java.util.ArrayList;

public class Station {
	private long id;
	private String name;
	private int type;
	private Line line;
	private int capacity;
	private int position;
	private ArrayList<Passenger> passengers;
	
	public Station(String name, int type, Line line, int capacity, int position){
		this.name = name;
		this.type = type;
		this.line = line;
		this.capacity = capacity;
		this.position = position;
	}
	
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
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
