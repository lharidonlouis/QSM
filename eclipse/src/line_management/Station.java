package line_management;

import java.util.ArrayList;

import passengers.Passenger;

/*
 * represents a train station
 */
public class Station {
	private int id;
	private String name;
	private int type;
	private Line line;
	private int capacity;
	private int position;
	private boolean[] tracksoccupied = {false, false};
	private ArrayList<Passenger> passengers;
	private boolean isBackup;
	private boolean[] isTerminus;
	private boolean[] isStart;
	
	/*
	 * creates a new station on the line at a given position
	 * with a new name and a given capacity, type,
	 * possession of backup tracks, and as start/terminus or not
	 */
	public Station(String name, int type, Line line, int capacity, int position, int id, boolean backup, boolean[] terminus, boolean[] start){
		this.name = name;
		this.type = type;
		this.line = line;
		this.capacity = capacity;
		this.position = position;
		this.id = id;
		isBackup = backup;
		isTerminus = terminus;
		isStart = start;
		passengers = new ArrayList<Passenger>();
	}
	
	/*
	 * allows the passengers on board of the train
	 * of which type matches the station's type to get off the train
	 * and the waiting passengers in the station to get in the train
	 */
	public void pickPassengers(Train train) {
		int i;
		ArrayList<Passenger> trainpassengers = train.getPassengers();
		for (i = 0; i < trainpassengers.size(); i++) {
			if (trainpassengers.get(i).getType() == type) {
				trainpassengers.remove(i);
			}
		}
		i = 0;
		while ((train.getPassengers().size() < train.getCapacity()) && (i < passengers.size())) {
			if(passengers.get(i).getWay() == train.getWay()) {
				train.getPassengers().add(passengers.get(i));
				passengers.remove(i);
			}
			i++;
		}
	}
	
	/*
	 * allows to add a passenger to the station
	 */
	public void addPassenger(Passenger p) {
		passengers.add(p);
	}
	
	/*
	 * makes a new train enter the station
	 * and wait if it is occupied until it is free
	 * then makes the train exit the canton it's leaving
	 * and marks the station as occupied
	 */
	public synchronized void enter(Train train) {
		if(tracksoccupied[train.getWay()]) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Canton oldcanton = train.getCurrentCanton();
		train.setCurrentStation(this);
		oldcanton.exit(train);
		setTrackOccupiedTrue(train.getWay());
	}
	
	/*
	 * sets the station that a train is leaving as not occupied
	 * and the train's current station as null
	 */
	public synchronized void exit(Train train) {
		int positionprevcanton;
		
		if (!isStart[train.getWay()]) {
			if (train.getWay() == 0)
				positionprevcanton = position - 1;
			else positionprevcanton = position + 1;

			Canton prevcanton = line.getCantonAtPosition(positionprevcanton, train.getWay());
			
			setTrackOccupiedFalse(train.getWay());
			train.setCurrentStation(null);
			
			prevcanton.wakeWaitingTrain();
		}
		else {
			setTrackOccupiedFalse(train.getWay());
			train.setCurrentStation(null);
		}
	}
	
	/*
	 * allows to check if a train is on a track of the station
	 */
	public boolean isTrackOccupied(int way) {
		return tracksoccupied[way];
	}

	/*
	 * sets the station's track index as occupied
	 */
	public void setTrackOccupiedTrue(int index) {
		tracksoccupied[index] = true;
	}
	
	/*
	 * sets the station's track index as not occupied
	 */
	public void setTrackOccupiedFalse(int index) {
		tracksoccupied[index] = false;
	}

	/*
	 * returns the station's position
	 */
	public int getPosition() {
		return position;
	}
	
	/*
	 * returns the line
	 */
	public Line getLine() {
		return line;
	}
	
	/*
	 * returns the station's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/*
	 * returns the passengers in the station
	 */
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	/*
	 * returns the station's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * returns the type
	 */
	public int getType() {
		return type;
	}
	
	/*
	 * returns the station's id
	 */
	public int getId() {
		return id;
	}

	/*
	 * allows to check if the station has backup tracks
	 */
	public boolean isBackup() {
		return isBackup;
	}

	/*
	 * allows to check if the station is a terminus for a given way
	 */
	public boolean isTerminus(int way) {
		return isTerminus[way];
	}

	/*
	 * allows to check if the station is a start for a given way
	 */
	public boolean isStart(int way) {
		return isStart[way];
	}
	
	/*
	 * sets or unsets the station as a start for a given way
	 */
	public void setStart(int way, boolean value) {
		isStart[way] = value;
	}
	
	/*
	 * sets or unsets the station as a terminus for a given way
	 */
	public void setTerminus(int way, boolean value) {
		isTerminus[way] = value;
	}
}
