package passengers;

import line_management.Line;
import line_management.Station;

public class PassengerGenerator extends Thread {
	private Line line;
	private int delay;
	
	public PassengerGenerator(Line line, int delay) {
		this.line= line;
		this.delay = delay;
	}
	
	public void run() {
		Station station;
		long id = 0;
		int type, way, i;
		
		while (true) {
			for (i = 0; i < line.getNbStations(); i++) {
				for (way = 0; way < 2; way++) {
					station = line.getStations().get(i);
					
					type = (int)(Math.random() * ((2 - 0) + 1));
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
}
