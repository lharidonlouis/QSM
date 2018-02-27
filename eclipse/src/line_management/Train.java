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
	
	/*
	 * creates a new train on the line
	 */
	public Train(int id, int way, Station station, int speed) {
		arrived = false;
		currentstation = station;
		this.line = station.getLine();
		this.id = id;
		this.way = way;
		this.position = station.getPosition();
		this.speed = speed;
		currentcanton = null;
	}
	
	/*
	 * main method to handle train movements
	 */
	public void run() {
		/*
		 * put the train on the first canton
		 */
		currentcanton.enter(this);
		while (!arrived) {
			try {
				sleep(speed);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			
			if (way == 0) {
				int nextposition = position + 1;
					if (currentstation != null) {
						try {
							Canton nextcanton = line.getCantonAtPosition(nextposition, way);
							nextcanton.enter(this);
							updatePosition();
						} catch (TrainArrivedException terminus) {
							arrived = true;
							/*
							 * Destroy train when getting out of the last station ?
							 */
						}
					}
					else if (currentcanton != null) {
						Station nextstation = line.getStationAtPosition(nextposition);
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
						try {
							Canton nextcanton = line.getCantonAtPosition(nextposition, way);
							nextcanton.enter(this);
							updatePosition();
						} catch (TrainArrivedException terminus) {
							arrived = true;
							/*
							 * Destroy train when getting out of the last station ?
							 */
						}
					}
					else if (currentcanton != null) {
						Station nextstation = line.getStationAtPosition(nextposition);
						nextstation.enter(this);
						updatePosition();
					}
					else {
						System.err.println("Train neither in station nor canton");
					}
			}
		}
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
}
