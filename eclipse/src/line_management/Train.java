package line_management;

import java.util.ArrayList;
import simulation.SimulationGUI;

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
	public Train(Line line, int id, int way, int position, int speed) {
		arrived = false;
		this.line = line;
		this.id = id;
		this.way = way;
		this.position = position;
		this.speed = speed;
		currentcanton = null;
		currentstation = null;
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
				sleep(SimulationGUI.DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			
			if (way == 0) {
				
			}
			else {
				
			}
		}
	}

	/*
	 * make the train move on the line
	 */
	public void updatePosition() {
		if (way == 0)
			position += speed;
		else position -= speed;
	}
	
	/*
	 * returns the train's current station
	 */
	public Station getCurrentstation() {
		return currentstation;
	}

	/*
	 * allows to set the train's current station
	 */
	public void setCurrentstation(Station currentstation) {
		this.currentstation = currentstation;
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
	 * 
	 * NEEDS CONCEPTUAL REVIEW
	 */
	public Station getCurrentStation() {
		return line.getStationAtPosition(position, speed);
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
