package incidents;

import java.util.ArrayList;
import java.util.Random;
import line_management.Line;

/**
 * Thread used to generate passengers in the simulation
 * 
 * @author bastien.ck
 * 
 * @see Thread
 */
public class IncidentGenerator extends Thread {
	/**
	 * Sleep duration for the thread
	 */
	private int delay;
	/**
	 * @see Line
	 */
	private Line line;
	/**
	 * boolean describing if the generator is running
	 */
	private boolean running;
	/**
	 * Array list of the incidents generated
	 */
	private ArrayList<Incident> incidents;
	
	/**
	 * Creates a new generator for incidents
	 * 
	 * @param delay
	 * duration for thread inactivity
	 * @param line
	 * line on which the incidents are to be generated
	 * @param incidents
	 * array list of incidents in the simulation
	 */
	public IncidentGenerator(int delay, Line line, ArrayList<Incident> incidents) {
		this.delay = delay;
		this.line = line;
		this.incidents = incidents;
		running = true;
	}
	
	/**
	 * main method, generates new incidents in stations and on cantons on a random basis
	 */
	public void run() {
		Incident result;
		Random r = new Random();
		int type, index, way;
		while(running) {
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			type = r.nextInt(2);
			way = r.nextInt(2);
			if (type == 0) {
				index = r.nextInt(line.getNbStations());
				result = new StationIncident(line, line.getStations().get(index), way);
			}
			else {
				index = r.nextInt(line.getNbSegments());
				result = new CantonIncident(line, line.getSegments().get(index).getCanton(way), way);
			}
			
			incidents.add(result);
		}
	}
}