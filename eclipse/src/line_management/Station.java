package line_management;

import java.util.ArrayList;


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
	private boolean[] tracksoccupied = new boolean[2];
	private ArrayList<Passenger> passengers;
	private boolean isBackup;
	private boolean[] isTerminus;
	private boolean[] isStart;
	
	/*
	 * creates a new station on the line at a given position
	 * with a new name and a given capacity and type
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
	 * makes a new train enter the station
	 * and wait if it is occupied until it is free
	 * then sets the old canton of the train as not occupied
	 * and the station as occupied
	 */
	public synchronized void enter(Train train) {
		while(tracksoccupied[train.getWay()]) {
			try {
				train.sleep(100);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		pickPassengers(train);
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
		train.setCurrentStation(null);
		setTrackOccupiedFalse(train.getWay());
		notify();
	}
	
	/*
	 * allows to check if a train is on a track of the station
	 */
	public boolean isTrackOccupied(int index) {
		return tracksoccupied[index];
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
	 * returns a short description of the station
	 */
	public String getDescription() {
		return "id : " + id + "\n\tposition : " + position + "\n\ttype : " + type + "\n\tcapacity : " + capacity;
	}

	public boolean isBackup() {
		return isBackup;
	}

	public boolean isTerminus(int index) {
		return isTerminus[index];
	}

	public boolean getIsStart(int index) {
		return isStart[index];
	}
	
	public void setStart(int index, boolean value) {
		isStart[index] = value;
	}
	
	public void setTerminus(int index, boolean value) {
		isTerminus[index] = value;
	}
}
