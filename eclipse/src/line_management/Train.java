package line_management;

import java.util.ArrayList;

import passengers.Passenger;

/**
 * represents a moving train on the line
 * with its speed, a list of passengers and a given way
 * 
 * @author bastien.ck
 */
public class Train extends Thread {
	/**
	 * the unique id of the train
	 * @see Train#getId()
	 */
	private long id;
	/**
	 * the way of the train on the line
	 * @see Train#getWay()
	 */
	private int way;
	/**
	 * the position of the train on the line
	 * @see Train#getPosition()
	 * @see Train#updatePosition()
	 */
	private int position;
	/**
	 * the speed of the train
	 * @see Train#getSpeed()
	 * @see Train#setSpeed(int)
	 */
	private int speed;
	/**
	 * true if the train has arrived to a terminus
	 * @see Train#hasArrived()
	 * @see Train#run()
	 */
	private boolean arrived;
	/**
	 * the capacity of the train
	 * @see Train#getCapacity()
	 */
	private int capacity;
	/**
	 * the canton that the train is on
	 * @see Train#getCurrentCanton()
	 * @see Train#setCurrentCanton(Canton)
	 */
	private Canton currentcanton;
	/**
	 * the station that the train is in
	 * @see Train#getCurrentStation()
	 * @see Train#setCurrentStation(Station)
	 */
	private Station currentstation;
	/**
	 * the list of passengers on the train
	 * @see Passenger
	 * @see Train#getPassengers()
	 */
	private ArrayList<Passenger> passengers;
	/**
	 * the line that the train is on
	 * @see Line
	 * @see Train#getLine()
	 */
	private Line line;
	/**
	 * true if an incident blocks the train
	 * @see Train#block()
	 * @see Train#unblock()
	 * @see Train#isBlocked()
	 */
	private boolean blocked;
	
	/**
	 * creates a new train on the line
	 * 
	 * @param id
	 * the unique id of the train on the line
	 * @param way
	 * the way of the train on the line
	 * @param station
	 * the start station for the train
	 * @param speed
	 * the speed of the train
	 * @param capacity
	 * the capacity of the train
	 */
	public Train(int id, int way, Station station, int speed, int capacity) {
		arrived = false;
		currentstation = station;
		currentstation.setTrackOccupiedTrue(way);
		line = station.getLine();
		this.id = id;
		this.way = way;
		this.position = station.getPosition();
		this.speed = speed;
		this.capacity = capacity;
		passengers = new ArrayList<Passenger>();
		currentcanton = null;
		blocked = false;
	}
	
	/**
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
							if (!currentstation.isTerminus(way)) {
								Canton nextcanton = line.getCantonAtPosition(nextposition, way);
								int indexcurrentstation = currentstation.getId();
								Station followingstation = line.getStations().get(indexcurrentstation + 1);
								if (nextcanton != null && followingstation != null) {
									nextcanton.enter(this, followingstation);
									updatePosition();
								}
							}
							else {
								arrived = true;
								Canton prevcanton = line.getCantonAtPosition(position - 1, way);
								prevcanton.wakeWaitingTrain();
							}
						}
						else if (currentcanton != null) {
							Station nextstation = line.getStationAtPosition(nextposition);
							if (nextstation != null) {
								nextstation.enter(this);
							}
							updatePosition();
						}
						else {
							System.err.println("Train neither in station nor canton");
						}
				}
				else {
					int nextposition = position - 1;
						if (currentstation != null) {
							if (!currentstation.isTerminus(way)) {
								Canton nextcanton = line.getCantonAtPosition(nextposition, way);
								int indexcurrentstation = currentstation.getId();
								Station followingstation = line.getStations().get(indexcurrentstation - 1);
								if(nextcanton != null && followingstation != null) {
									nextcanton.enter(this, followingstation);
									updatePosition();
								}
							}
							else {
								arrived = true;
								Canton prevcanton = line.getCantonAtPosition(position + 1, way);
								prevcanton.wakeWaitingTrain();
							}
						}
						else if (currentcanton != null) {
							Station nextstation = line.getStationAtPosition(nextposition);
							if (nextstation != null) {
								nextstation.enter(this);
							}
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

	/**
	 * make the train move on the line
	 */
	public void updatePosition() {
		if (way == 0)
			position += 1;
		else position -= 1;
	}

	/**
	 * @return the current canton of the train
	 */
	public Canton getCurrentCanton() {
		return currentcanton;
	}
	
	/**
	 * changes the train's current canton
	 * @param canton
	 * the new canton of the train
	 */
	public void setCurrentCanton(Canton canton) {
		currentcanton = canton;
	}
	
	/**
	 * @return the current station of the train
	 */
	public Station getCurrentStation() {
		return currentstation;
	}
	
	/**
	 * changes the train's current station
	 * @param station
	 * the new station of the train
	 */
	public void setCurrentStation(Station station) {
		currentstation = station;
	}
	
	/**
	 * @return the train's id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the train's way
	 */
	public int getWay() {
		return way;
	}
	
	/**
	 * @return the train's position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * changes the speed
	 * @param speed
	 * the new speed of the train
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * @return the train's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the list of passengers in the train
	 */
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * @return if the train is blocked by an incident
	 */
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * blocks the train when an incident occurs
	 */
	public void block() {
		blocked = true;
	}
	
	/**
	 * unblocks the train when an incident is solved
	 */
	public void unblock() {
		blocked = false;
	}

	/**
	 * @return if the train has arrived to a terminus
	 */
	public boolean hasArrived() {
		return arrived;
	}
}