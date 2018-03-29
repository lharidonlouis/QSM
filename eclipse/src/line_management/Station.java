package line_management;

import java.util.ArrayList;

import passengers.Passenger;

/**
 * represents a train station
 * 
 * @author bastien.ck
 */
public class Station {
	/**
	 * unique id of the station on the line
	 * @see Station#getId()
	 */
	private int id;
	/**
	 * name of the station
	 * @see Station#getName()
	 */
	private String name;
	/**
	 * type of the station
	 * @see Station#getType()
	 */
	private int type;
	/**
	 * line on which the station is
	 * @see Line
	 * @see Station#getLine()
	 */
	private Line line;
	/**
	 * capacity of the station
	 * @see Station#getCapacity()
	 * @see Station#pickPassengers(Train)
	 */
	private int capacity;
	/**
	 * position of the station on the line
	 * @see Station#getPosition()
	 */
	private int position;
	/**
	 * tab describing the occupation of the tracks in the station
	 * @see Station#isTrackOccupied(int)
	 * @see Station#setTrackOccupiedTrue(int)
	 * @see Station#setTrackOccupiedFalse(int)
	 */
	private boolean[] tracksoccupied = {false, false};
	/**
	 * the list of passengers in the station
	 * @see Passenger
	 * @see Station#pickPassengers(Train)
	 * @see Station#addPassenger(Passenger)
	 * @see Station#getPassengers()
	 */
	private ArrayList<Passenger> passengers;
	/**
	 * describes the presence of backup tracks at the station
	 * @see Station#isBackup()
	 */
	private boolean isBackup;
	/**
	 * true if the station is terminus for a given way
	 * @see Station#isTerminus(int)
	 * @see Station#setTerminus(int, boolean)
	 */
	private boolean[] isTerminus;
	/**
	 * true if the station is start for a given way
	 * @see Station#isStart(int)
	 * @see Station#setStart(int, boolean)
	 */
	private boolean[] isStart;
	
	/**
	 * creates a new station
	 * 
	 * @param name
	 * the name of the station
	 * @param type
	 * the type of the station
	 * @param line
	 * the line that the station belongs to
	 * @param capacity
	 * the capacity of the station
	 * @param position
	 * the position of the station on the line
	 * @param id
	 * the unique id of the station on the line
	 * @param backup
	 * the presence of backup tracks at this station
	 * @param terminus
	 * true if the station is terminus for a given way
	 * @param start
	 * true if the station is start for a given way
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
	
	/**
	 * allows the passengers on board of the train
	 * of which type matches the station's type to get off the train
	 * and the waiting passengers in the station to get in the train
	 * 
	 * @param train
	 * the train picking up passengers
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
	
	/**
	 * allows to add a passenger to the station
	 * @param p
	 * the passenger to add
	 */
	public void addPassenger(Passenger p) {
		if(passengers.size() < capacity) {
			passengers.add(p);
		}
	}
	
	/**
	 * makes a new train enter the station
	 * and wait if it is occupied until it is free
	 * then makes the train exit the canton it's leaving
	 * and marks the station as occupied
	 * 
	 * @param train
	 * the train to enter the station
	 */
	public synchronized void enter(Train train) {
		if(tracksoccupied[train.getWay()]) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		try {
			train.sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}

		Canton oldcanton = train.getCurrentCanton();
		train.setCurrentStation(this);
		oldcanton.exit(train);
		setTrackOccupiedTrue(train.getWay());
		pickPassengers(train);
	}
	
	/**
	 * sets the station that a train is leaving as not occupied
	 * and the train's current station as null
	 * allows the waiting trains to enter the previous canton
	 * 
	 * @param train
	 * the train to exit the station
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
	
	/**
	 * allows to check if a train is on a track of the station
	 * @param way
	 * the track to check
	 */
	public boolean isTrackOccupied(int way) {
		return tracksoccupied[way];
	}

	/**
	 * sets the station's track index as occupied
	 * @param index
	 * the track to set as occupied
	 */
	public void setTrackOccupiedTrue(int index) {
		tracksoccupied[index] = true;
	}
	
	/**
	 * sets the station's track index as not occupied
	 * @param index
	 * the track to set as not occupied
	 */
	public void setTrackOccupiedFalse(int index) {
		tracksoccupied[index] = false;
	}

	/**
	 * @return the station's position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * @return the station's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the passengers in the station
	 */
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	/**
	 * @return the station's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @return the station's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return if the station has backup tracks
	 */
	public boolean isBackup() {
		return isBackup;
	}

	/**
	 * @param way
	 * the checked way
	 * @return if the station is a terminus for a given way
	 */
	public boolean isTerminus(int way) {
		return isTerminus[way];
	}

	/**
	 * @param way
	 * the checked way
	 * @return if the station is a start for a given way
	 */
	public boolean isStart(int way) {
		return isStart[way];
	}
	
	/**
	 * sets or unsets the station as a start for a given way
	 * @param way
	 * the changed way
	 * @param value
	 * true or false
	 */
	public void setStart(int way, boolean value) {
		isStart[way] = value;
	}
	
	/**
	 * sets or unsets the station as a terminus for a given way
	 * @param way
	 * the changed way
	 * @param value
	 * true or false
	 */
	public void setTerminus(int way, boolean value) {
		isTerminus[way] = value;
	}
}
