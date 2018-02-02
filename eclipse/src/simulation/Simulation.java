package simulation;

import java.util.List;

import line_management.Line;
import line_management.Train;
import line_management.Builder;

public class Simulation implements Runnable {
	private Line line;
	private List<Train> trains;
	
	public Simulation() {
		Builder builder = new Builder();
		//builder.build
	}
	
	@Override
	public void run() {
		
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
