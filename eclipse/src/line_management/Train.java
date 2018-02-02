package line_management;

import java.util.List;

public class Train {
	private long id;
	private String destination;
	private int position;
	private int speed;
	private int capacity;
	private List<Passenger> passengers;
	private Line line;
	
	public Train() {
		
	}
	
	public Canton getCurrentCanton() {
		return line.getCantonAtPosition(position);
	}
	
	public Station getCurrentStation() {
		return line.getStationAtPosition(position);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
}
