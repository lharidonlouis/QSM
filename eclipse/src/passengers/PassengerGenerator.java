package passengers;

import java.util.Random;

import line_management.Line;
import line_management.Station;

/**
 * Thread used to generate passengers in the simulation
 * 
 * @author bastien.ck
 * 
 * @see Thread
 */
public class PassengerGenerator extends Thread {
	/**
	 * @see Line
	 */
	private Line line;
	private int delay;
	/**
	 * @see PassengerGenerator#PassengerGenerator(Line, int)
	 * @see PassengerGenerator#end()
	 */
	private boolean running;
	
	/**
	 * Creates a new generator
	 * @param line
	 * line to add passengers to
	 * @param delay
	 * delay between each adding of passengers
	 */
	public PassengerGenerator(Line line, int delay) {
		this.line= line;
		this.delay = delay;
		running = true;
	}
	
	/**
	 * method describing the execution of the thread
	 */
	public void run() {
		Station station;
		long id = 0;
		int type, way, i;
		Random r = new Random();
		
		while (running) {
			for (i = 0; i < line.getNbStations(); i++) {
				for (way = 0; way < 2; way++) {
					station = line.getStations().get(i);
					
					type = r.nextInt(3);
					Passenger passenger = new Passenger(type, id, way);
					
					station.addPassenger(passenger);
					id++;
				}
			}
			
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * method used to end the generation of passengers
	 */
	public void end() {
		running = false;
	}
}
