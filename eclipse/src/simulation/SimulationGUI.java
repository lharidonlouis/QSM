package simulation;

import java.util.ArrayList;
import javax.swing.JFrame;
import line_management.Station;
import line_management.Train;

public class SimulationGUI extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int DELAY = 100;
	public static final int REGULAR_SPEED = 30;
	public static final int CAPACITY = 20;
	
	private Dashboard dashboard;
	private ArrayList<Station> starts;
	private int currentId;
	
	public SimulationGUI() {
		super("Train simulation");
		dashboard = new Dashboard();
		getContentPane().add(dashboard);
		setSize(1150, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}

	@Override
	public void run() {	
		currentId = 0;
		int way;

		while(true) {
			starts = getStarts();
			for (Station station : starts) {
				for(way = 0; way < 2; way++) {
					Train newtrain = null;
					if (station.isStart(way) && !station.isTerminus(way)) {
						if (!station.isTrackOccupied(way)) {
							newtrain = new Train(currentId, way, station, REGULAR_SPEED, CAPACITY);
						}
					}
					
					if(newtrain != null){
						dashboard.addTrain(newtrain);
						currentId++;
					}
				}
				
			}
			dashboard.repaint();
			dashboard.refreshTrains();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
    }
	
	public ArrayList<Station> getStarts() {
		ArrayList<Station> stations = new ArrayList<Station>();
		
		for (Station station : dashboard.getLine().getStations()) {
			for(int way = 0; way < 2; way++) {
				if (station.isStart(way) && !station.isTerminus(way))
					stations.add(station);
			}
		}
		
		return stations;
	}
}