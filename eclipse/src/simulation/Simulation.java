package simulation;

import java.util.List;
import line_management.Line;
import line_management.Train;
import line_management.Builder;
import line_management.Canton;

public class Simulation implements Runnable{
	private Line line;
	private List<Train> trains;
	public static final int DELAY = 50;
	private static final int REGULAR_SPEED = 2;
	
	public Simulation() {
		Builder builder = new Builder();
		
		/* ADD LINE BUILD */
		/* builder.build */
	}
	
	@Override
	public void run() {
		while(true) {
			Canton firstCanton = line.getCantons().get(0);
			Canton lastCanton = line.getCantons().get(line.getNbCantons());
			if (!firstCanton.isOccupied()){
				String destination = "end";
				int position = 0;
				
				Train newtrain = new Train(line, destination, position, REGULAR_SPEED);
				addTrain(newtrain);
				newtrain.start();
				
				/* HANDLE SPEED VARIATIONS */
			}
			else if (!lastCanton.isOccupied()){
				String destination = "beginning";
				int position = line.getLength();
				
				Train newtrain = new Train(line, destination, position, REGULAR_SPEED);
				addTrain(newtrain);
				newtrain.start();
				
				/* HANDLE SPEED VARIATIONS */
			}
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void addTrain(Train train) {
		trains.add(train);
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public List<Train> getTrains() {
		return trains;
	}
	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
}
