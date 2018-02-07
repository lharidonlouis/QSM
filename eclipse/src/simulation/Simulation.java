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
	public static final int REGULAR_SPEED = 2;
	
	public Simulation() {
		Builder builder = new Builder();
		
		builder.build(1000);
		if (builder.isBuilt())
			line = builder.getLine();
	}
	
	@Override
	public void run() {
		while(true) {
			Canton[] firstCantons = new Canton[2];
			firstCantons[0] = line.getSegments().get(0).getCanton(0);
			firstCantons[1] = line.getSegments().get(line.getNbSegments()).getCanton(1);
			int position;
			
			for (int i = 0; i < 2; i++) {
				if (!firstCantons[i].isOccupied()) {
					if (i == 0)
						position = 0;
					else position = line.getLength();
					Train newtrain = new Train(line, i, position, REGULAR_SPEED);
					addTrain(newtrain);
					newtrain.start();
					
					/* HANDLE SPEED VARIATIONS */
				}
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
