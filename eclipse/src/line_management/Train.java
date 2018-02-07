package line_management;

import java.util.List;

import simulation.Simulation;

public class Train extends Thread {
	private long id;
	private int way;
	private int position;
	private int speed;
	private boolean arrived;
	private int capacity;
	private Canton currentcanton;
	private List<Passenger> passengers;
	private Line line;
	
	public Train(Line line, int way, int position, int speed) {
		this.line = line;
		this.way = way;
		this.position = position;
		this.speed = speed;
	}
	
	public void run() {
		while (!arrived) {
			try {
				sleep(Simulation.DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			if (way == 0) {
				if (position + speed >= line.getSegmentAtPosition(position).getEndPoint()) {
					try {
						Canton nextcanton = line.getCantonAtPosition(position + speed, way);
						if (nextcanton.isOccupied()) {
							setPosition(line.getSegmentAtPosition(position + speed).getStartPoint() - 1);
							try {
								wait();
							} catch (InterruptedException e) {
								System.err.println(e.getMessage());
							}
						}
						Canton oldcanton = getCurrentCanton();
						nextcanton.setOccupiedTrue();
						updatePosition();
						oldcanton.setOccupiedFalse();
					} catch (TrainArrivedException e) {
						arrived = true;
						setPosition(line.getLength());
					}
				}
				else {
					updatePosition();
				}
			}
			else {
				if (position - speed <= line.getSegmentAtPosition(position).getStartPoint()) {
					try {
						Canton nextcanton = line.getCantonAtPosition(position - speed, way);
						if (nextcanton.isOccupied()) {
							setPosition(line.getSegmentAtPosition(position + speed).getEndPoint() + 1);
							try {
								wait();
							} catch (InterruptedException e) {
								System.err.println(e.getMessage());
							}
						}
						Canton oldcanton = getCurrentCanton();
						nextcanton.setOccupiedTrue();
						updatePosition();
						oldcanton.setOccupiedFalse();
					} catch (TrainArrivedException e) {
						arrived = true;
						setPosition(line.getLength());
					}
				}
				else {
					updatePosition();
				}
			}
		}
		currentcanton.setOccupiedFalse();
	}

	public void updatePosition() {
		if (way == 0)
			position += speed;
		else position -= speed;
	}

	public Canton getCurrentCanton() {
		return currentcanton;
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
	public int getWay() {
		return way;
	}
	public void setWat(int way) {
		this.way = way;
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
