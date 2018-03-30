package simulation;

import java.util.ArrayList;
import javax.swing.JFrame;

import incidents.Incident;
import incidents.IncidentGenerator;
import line_management.Station;
import line_management.Train;
import passengers.PassengerGenerator;

public class SimulationGUI extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int DELAY = 100;
	public static final int REGULAR_SPEED = 5;
	public static final int CAPACITY = 50;
	public static final ArrayList<Incident> incidents = new ArrayList<Incident>();
	
	private Dashboard dashboard;
	private ArrayList<Station> starts;
	private int currentId;
	
	public SimulationGUI() {
		super("Train simulation");
		dashboard = new Dashboard();
		getContentPane().add(dashboard);
		setSize(1920, 1080);
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
		
		IncidentGenerator ig = new IncidentGenerator(10000,dashboard.getLine(), incidents);
		ig.start();
		PassengerGenerator pg = new PassengerGenerator(dashboard.getLine() , 1000);
		pg.start();

		while(true) {
			starts = getStarts();
			for (Station station : starts) {
				for(way = 0; way < 2; way++) {
					Train newtrain = null;
					if (station.isStart(way) && !station.isTerminus(way)) {
						if (station.getTrainOnTrack(way) == null) {
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
