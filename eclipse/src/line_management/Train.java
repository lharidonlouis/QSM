package line_management;

import java.util.List;

import simulation.Simulation;

public class Train extends Thread {
	private long id;
	private String destination;
	private int position;
	private int speed;
	private boolean arrived;
	private int capacity;
	private List<Passenger> passengers;
	private Line line;
	
	public Train(Line line, String destination, int position, int speed) {
		this.line = line;
		this.destination = destination;
		this.position = position;
		this.speed = speed;
	}
	
	public void run() {
		while(!arrived) {
			try {
				sleep(Simulation.DELAY);
			} catch (InterruptedException e){
					System.err.println(e.getMessage());
			}
			if (position + speed >= line.getCantonAtPosition(position).getEndPosition()){
				Canton nextCanton = line.getCantonAtPosition(position + speed);

				/* CHECK THIS PART */
				nextCanton.addTrain();
				arrived = true;
				position = line.getLength();
				/* END CHECK */
			} else {
				updatePosition();
			}
		}
		line.getCantonAtPosition(position).removeTrain();
	}

	public void updatePosition() {
		position += speed;
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
	public boolean isArrived() {
		return arrived;
	}
	public void setArrivedTrue() {
		this.arrived = true;
	}
	public void setArrivedFalse() {
		this.arrived = false;
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
