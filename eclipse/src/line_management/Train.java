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
	private ArrayList<Passenger> passengers;
	private Line line;
	
	/*
	 * creates a new train on the line
	 */
	public Train(Line line, int id, int way, int position, int speed, Canton canton) {
		arrived = false;
		this.line = line;
		this.id = id;
		this.way = way;
		this.position = position;
		this.speed = speed;
		this.currentcanton = canton;
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
				/*
				 * if the train reaches the end of the segment it is on
				 */
				if (position + speed > line.getSegmentAtPosition(position).getEndPoint()) {
					try {
						/*
						 * pick passengers at the current station
						 */
						Station currentStation = getCurrentStation();
						currentStation.pickPassengers(this);
						
						/*
						 * enter the next canton on the line
						 */
						Canton nextcanton = line.getCantonAtPosition(position + speed, way);
						nextcanton.enter(this);
					} catch (TrainArrivedException e) {
						/*
						 * if the train as reached the end of the line
						 * exit the last canton and set the train as arrived
						 */
						arrived = true;
						setPosition(line.getLength());
						currentcanton.exit();
					}
				}
				else {
					/*
					 * make the train move
					 */
					updatePosition();
				}
			}
			else {
				/*
				 * if the train reaches the end of the segment it is on
				 * 
				 * NEEDS REFACTOR TO HANDLE SEGMENT -1
				 */
				if (position - speed < line.getSegmentAtPosition(position).getStartPoint()) {
						/*
						 * if the train as reached the end of the line
						 * exit the last canton and set the train as arrived
						 */
						if( (position-speed)<0) {
							arrived = true;
							setPosition(line.getLength());
							currentcanton.exit();
						}
						else {
							try{
								/*
								 * pick passengers at the current station
								 */
								Station currentStation = getCurrentStation();
								currentStation.pickPassengers(this);
								

								/*
								 * enter the next canton on the line
								 */
								Canton nextcanton = line.getCantonAtPosition(position - speed, way);
								nextcanton.enter(this);
							} catch (TrainArrivedException e) { }
						}
				}
				else {
					/*
					 * make the train move
					 */
					updatePosition();
				}
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
