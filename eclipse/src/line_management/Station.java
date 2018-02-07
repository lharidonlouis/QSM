package line_management;

import java.util.List;

public class Station {
	private long id;
	private String name;
	private int type;
	private Line line;
	private int capacity;
	private int position;
	private List<Passenger> passengers;
	
	public Station(String name, int type, Line line, int capacity, int position){
		this.name = name;
		this.type = type;
		this.line = line;
		this.capacity = capacity;
		this.position = position;
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
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
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
