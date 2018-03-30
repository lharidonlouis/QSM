package incidents;

import java.util.ArrayList;
import java.util.Random;
import line_management.Line;

public class IncidentGenerator extends Thread {
	private int delay;
	private Line line;
	private boolean running;
	private ArrayList<Incident> incidents;
	
	public IncidentGenerator(int delay, Line line, ArrayList<Incident> incidents) {
		this.delay = delay;
		this.line = line;
		this.incidents = incidents;
		running = true;
	}
	
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
	
	public void solveAllProblems() {
		for (Incident incident : incidents) {
			incident.terminate();
		}
	}
}