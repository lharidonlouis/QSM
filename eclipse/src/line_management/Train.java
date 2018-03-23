package line_management;

import java.util.ArrayList;

/*
 * represents a moving train on the line
 * with its speed, a list of passengers and a given way
 */
public class Train extends Thread {
	private long id;
	private int way;
	private int position;
	private int speed;
	private boolean arrived;
	private int capacity;
	private Canton currentcanton;
	private Station currentstation;
	private ArrayList<Passenger> passengers;
	private Line line;
	private boolean blocked;
	
	/*
	 * creates a new train on the line
	 */
	public Train(int id, int way, Station station, int speed, int capacity) {
		arrived = false;
		currentstation = station;
		currentstation.setTrackOccupiedTrue(way);
		this.line = station.getLine();
		this.id = id;
		this.way = way;
		this.position = station.getPosition();
		this.speed = speed;
		this.capacity = capacity;
		passengers = new ArrayList<Passenger>();
		currentcanton = null;
		blocked = false;
	}
	
	/*
	 * main method to handle train movements
	 */
	public void run() {
		while (!arrived) {
			if (!blocked) {
				try {
					sleep(speed);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				if (way == 0) {
					int nextposition = position + 1;
						if (currentstation != null) {
							Canton nextcanton = line.getCantonAtPosition(nextposition, way);
							int nextstationposition = line.getSegmentForCanton(nextcanton).getEndPoint() + 1;
							Station followingstation = line.getStationAtPosition(nextstationposition);
							if (nextcanton != null && followingstation != null) {
								nextcanton.enter(this, followingstation);
								updatePosition();
							}
							else arrived = true;
						}
						else if (currentcanton != null) {
							Station nextstation = line.getStationAtPosition(nextposition);
							if (nextstation != null)
								nextstation.enter(this);
							updatePosition();
						}
						else {
							System.err.println("Train neither in station nor canton");
						}
				}
				else {
					int nextposition = position - 1;
						if (currentstation != null) {
							Canton nextcanton = line.getCantonAtPosition(nextposition, way);
							int nextstationposition = line.getSegmentForCanton(nextcanton).getStartPoint() - 1;
							Station followingstation = line.getStationAtPosition(nextstationposition);
							if(nextcanton != null && followingstation != null) {
								nextcanton.enter(this, followingstation);
								updatePosition();
							}
							else arrived = true;
						}
						else if (currentcanton != null) {
							Station nextstation = line.getStationAtPosition(nextposition);
							if (nextstation != null)
								nextstation.enter(this);
							updatePosition();
						}
						else {
							System.err.println("Train neither in station nor canton");
						}
				}
			}
		}
		line.getStationAtPosition(position).setTrackOccupiedFalse(way);
		
		if (way == 0)
			line.getCantonAtPosition((position - 1), way).setOccupiedFalse();
		else
			line.getCantonAtPosition((position + 1), way).setOccupiedFalse();
	}

	/*
	 * make the train move on the line
	 */
	public void updatePosition() {
		if (way == 0)
			position += 1;
		else position -= 1;
	}

	/*
	 * returns the current canton of the train
	 */
	public Canton getCurrentCanton() {
		return currentcanton;
	}
	
	/*
	 * changes the train's current canton
	 */
	public void setCurrentCanton(Canton canton) {
		currentcanton = canton;
	}
	
	/*
	 * returns the current station of the train
	 */
	public Station getCurrentStation() {
		return currentstation;
	}
	
	/*
	 * changes the train's current station
	 */
	public void setCurrentStation(Station station) {
		currentstation = station;
	}
	
	/*
	 * returns the train's id
	 */
	public long getId() {
		return id;
	}
	
	/*
	 * returns the train's way
	 */
	public int getWay() {
		return way;
	}
	
	/*
	 * returns the train's position
	 */
	public int getPosition() {
		return position;
	}
	
	/*
	 * changes the train's position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/*
	 * returns the speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/*
	 * changes the speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	 * returns the train's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/*
	 * returns the passengers in the train
	 */
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	/*
	 * returns the line
	 */
	public Line getLine() {
		return line;
	}
	
	/*
	 * blocks the train
	 */
	public void block() {
		blocked = true;
	}
	
	/*
	 * unblocks the train
	 */
	public void unblock() {
		blocked = false;
	}

	/*
	 * allows to check if the train has arrived to a terminus
	 */
	public boolean hasArrived() {
		return arrived;
	}
}
