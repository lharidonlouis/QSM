package line_management;

import java.util.ArrayList;
import timetable.Clock;


import static java.lang.Thread.sleep;

import java.util.Random;

import simulation.SimulationGUI;
import line_management.IncidentException;

public class Train extends Thread implements Runnable {
    private final static int DOWNTIME = 120;
    private final static Clock CLOCK = Clock.getINSTANCE();

	private long id;
	private int way;
	private int position;
	private int speed;
	private int waitedTime = 0;
	private boolean arrived;
    private boolean Stoped = false;
    private boolean inStation = false;
	private int capacity;
	private Canton currentcanton;
	private ArrayList<Passenger> passengers;
	private Line line;
	

	
	public Train(Line line, int id, int way, int position, int speed, Canton canton) {
		this.line = line;
		this.id = id;
		this.way = way;
		this.position = position;
		this.speed = speed;
		this.currentcanton = canton;
	}
	
	// stop the train due to an accident
	
    public void stop (Incident incident) throws IncidentException {
    	throw new IncidentException(incident.toString());
    }
    
	public void run() {
		currentcanton.enter(this);
		while (!arrived) {
			try {
				if(Stoped){
					System.out.println("train arrêté");
					Random r = new Random();
					sleep(r.nextInt(10000));
                    Stoped = false;
                    if (currentcanton.hasStation()) {
                        currentcanton.generateException(Incident.OK);
                    }
				}
                else {
                        synchronized (CLOCK){
                            CLOCK.wait();
                        }
                        if (inStation) {
                            waitedTime++;
                            if (waitedTime > DOWNTIME) {
                                waitedTime = 0;
                                inStation = false;
                            }
                        }
                        else {
                            updatePosition();
                        }
                        Random r = new Random();
                        if (r.nextInt(1000) == 666) {
                            //canton.generateException(Incident.SUICIDE);
                        	System.out.println("suicide generated");
                        }
				}
			sleep(SimulationGUI.DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			} catch (IncidentException e) {
				// TODO Auto-generated catch block
				Stoped = true;
			}
			  catch (Exception e) {
	                e.printStackTrace();
	            }
			if (way == 0) {
				System.out.println("Train nÂ°" + this.getId() + "[speed:" + speed + "]" + " position : " + position + " way : " + way + " Segment : " + line.getSegmentAtPosition(position).getId());
				if (position + speed > line.getSegmentAtPosition(position).getEndPoint()) {
					try {
						/*
						 * Passenger handling
						 */
						Station currentStation = getCurrentStation();
						currentStation.pickPassengers(this);
						
						System.out.println("Train " + this.getId() + " arrived at station : " + currentStation.getName().toString());
						Canton nextcanton = line.getCantonAtPosition(position + speed, way);
						nextcanton.enter(this);
					} catch (TrainArrivedException e) { }
					catch (java.lang.IndexOutOfBoundsException e) {
						arrived = true;
						setPosition(line.getLength());
						this.currentcanton.exit();
						System.out.println("train " +   getId() + " arrived");
						this.stop();
					}
				}
				else {
					updatePosition();
				}
			}
			else {
				System.out.println("Train nÂ°" + this.getId() + "[speed:-" + speed + "]" + " position : " + position + " way : " + way + " Segment : " + line.getSegmentAtPosition(position).getId());
				if (position - speed < line.getSegmentAtPosition(position).getStartPoint()) {
						if( (position-speed)<0) {
							arrived = true;
							setPosition(line.getLength());
							this.currentcanton.exit();
							System.out.println("train " +   getId() + " arrived");
							this.stop();
						}
						else {
							try{
								/*
								 * Passenger handling
								 */
								Station currentStation = getCurrentStation();
								currentStation.pickPassengers(this);
								
								System.out.println("Train " + this.getId() + " arrived at station : " + currentStation.getName().toString());
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
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
}
