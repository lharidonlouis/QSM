package line_management;

import java.util.List;
import simulation.SimulationGUI;

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
	
	public Train(Line line, int id, int way, int position, int speed, Canton canton) {
		this.line = line;
		this.id = id;
		this.way = way;
		this.position = position;
		this.speed = speed;
		this.currentcanton = canton;
	}
	
	public void run() {
		while (!arrived) {
			try {
				sleep(SimulationGUI.DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			if (way == 0) {
				System.out.println("Train n°" + this.getId() + "[speed:" + speed + "]" + " position : " + position + " way : " + way + " Segment : " + line.getSegmentAtPosition(position).getId());
				if (position + speed > line.getSegmentAtPosition(position).getEndPoint()) {
					System.out.println("Train " + this.getId() + " arrived at station : " + getCurrentStation().getName().toString() + " on position : " + getCurrentStation().getPosition());
					try {
						System.out.println("Train " + this.getId() + " arrived at station : " + getCurrentStation().getName().toString());
						Canton nextcanton = line.getCantonAtPosition(position + speed, way);
						nextcanton.enter(this);
					} catch (TrainArrivedException e) { }
					catch (java.lang.IndexOutOfBoundsException e) {
						arrived = true;
						setPosition(line.getLength());
						System.out.println("train " +   getId() + " arrived");
					}
				}
				else {
					updatePosition();
				}
			}
			else {
				System.out.println("Train n°" + this.getId() + "[speed:-" + speed + "]" + " position : " + position + " way : " + way + " Segment : " + line.getSegmentAtPosition(position).getId());
				if (position - speed < line.getSegmentAtPosition(position).getStartPoint()) {
						if( (position-speed)<0) {
							arrived = true;
							setPosition(line.getLength());
							System.out.println("train " +   getId() + " arrived");
						}
						else {
							try{
								System.out.println("Train " + this.getId() + " arrived at station : " + getCurrentStation().getName().toString());
								Canton nextcanton = line.getCantonAtPosition(position - speed, way);
								nextcanton.enter(this);
							} catch (TrainArrivedException e) { }							
						}
				}
				else {
					updatePosition();
				}
			}
		}
		currentcanton.exit();
	}

	public void updatePosition() {
		if (way == 0)
			position += speed;
		else position -= speed;
	}

	public Canton getCurrentCanton() {
		return currentcanton;
	}
	
	public void setCurrentCanton(Canton canton) {
		currentcanton = canton;
	}
	
	public Station getCurrentStation() {
		return line.getStationAtPosition(position, speed);
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
