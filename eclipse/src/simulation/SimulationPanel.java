package simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import line_management.Train;

public class SimulationPanel extends JPanel{
	private Train test;
	private static final int START_X = 20;
	private static final int START_Y = 200;
	
	public SimulationPanel(){
		Simulation simulation=new Simulation();
	}
}
