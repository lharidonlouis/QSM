package simulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;

import line_management.Canton;
import line_management.Line;
import line_management.Train;
import line_management.TrainArrivedException;

public class SimulationGUI extends JFrame implements Runnable{
	private Dashboard dashboard=new Dashboard();
	public static final int DELAY = 250;
	public static final int REGULAR_SPEED = 10;
	public static final int CAP = 20;
	private Line line;
	
	public SimulationGUI() {
		super("Train simulation");
		getContentPane().add(dashboard);
		setSize(1280, 800);
		setVisible(true);
	}

	
	public static void main(String[] args) {
		SimulationGUI simulationGUI = new SimulationGUI();
		Thread simulationThread = new Thread(simulationGUI);
		simulationThread.start();
	}
	

	@Override
	public void run()  {
		// TODO Auto-generated method stub		
		line = dashboard.getLine();
		int id = 0;
		System.out.println("Debut de la fonction");

		while(true) {
			System.out.println("Debut de la boucle");
			Canton[] terminus = new Canton[2];
			
			try {
				terminus[0] = line.getCantonAtPosition(1, 0);
			} catch (TrainArrivedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("On a le terminus 1");
			try {
				terminus[1] = line.getCantonAtPosition(line.getLength()-2, 1);
			} catch (TrainArrivedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("On a le terminus 2");
			System.out.println("Canton de depart " + terminus.toString() );

			int position;
			System.out.println("Sens " + 0 + " occuped : " +  terminus[0].isOccupied() );
			System.out.println("Sens " + 1 + " occuped : " +  terminus[1].isOccupied() );
			for(int i =0; i<2; i++) {
				System.out.println("i : " + i);
				Train newtrain = null;
				if(!terminus[i].isOccupied()) {
					System.out.println("terminus i  : " + i + " " + terminus[i].isOccupied());
					switch (i) {
						case 0:
								position = 0;
								//System.out.println("station info : " + line.getStationAtPosition(position).getDescription());
								if(!line.getStationAtPosition(position).isTrackOccupied(i)) {
									newtrain = new Train(id, i, line.getStationAtPosition(position), REGULAR_SPEED, CAP);
								}
						break;
						case 1:
								position = line.getLength()-1;
							//	System.out.println("station info : " + line.getStationAtPosition(position).getDescription());
								if(!line.getStationAtPosition(position).isTrackOccupied(i)) {
									newtrain = new Train(id, i, line.getStationAtPosition(position), REGULAR_SPEED, CAP);
								}
						break;
					}
				}
				if(newtrain != null){
					System.out.println("Train " + id + " successfully created");
					newtrain.start();
					dashboard.trains.add(newtrain);
					id++;
				}
			}
			//dashboard.refresh();
			dashboard.repaint();
			try {
				Thread.sleep(DELAY);
				System.out.println(" ");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
    }
}