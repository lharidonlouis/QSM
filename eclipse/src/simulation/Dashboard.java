package simulation;

import line_management.Train;
import line_management.Line;
import line_management.Segment;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import line_management.Builder;

public class Dashboard  extends JPanel{
	private static final long serialVersionUID = 1L;
	private Line line;
	public ArrayList<Train> trains = new ArrayList<Train>();
	private static final int START_X = 50;
	private static final int LENGTH = 1000;
	private static final int START_Y = 150;
	private double ratio;

	public Dashboard(){
		Builder builder = new Builder();
		builder.build(5);
		line = builder.getLine();

		ratio = (double) LENGTH / (double)line.getLength();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		printLine(g2);
		printTrains(g2);
	}

	private void printLine(Graphics2D g2) {
		Segment segment;
		
		g2.setStroke(new BasicStroke(8));
		double current_position = START_X;
		g2.drawString("Station 0", START_X - 20, START_Y + 30);
		
		g2.drawLine(START_X, START_Y, START_X + LENGTH, START_Y);
		g2.drawLine(START_X, START_Y + 50, START_X + LENGTH, START_Y + 50);
		
		// Station 0, way 0
		g2.drawLine(START_X, START_Y - 10, START_X, START_Y + 10);
		// Station 0, way 1
		g2.drawLine(START_X, START_Y + 40, START_X, START_Y + 60);
		
		for (int segmentId = 0; segmentId < line.getNbSegments(); segmentId++) {
			segment = line.getSegments().get(segmentId);
			
			current_position += (double)segment.getLength() * ratio;
			
			int n = segment.getId() + 1;
			
			g2.drawString("Station " + n, (int)current_position - 20, START_Y + 30);
			
			// Stations
			if (segmentId != line.getNbSegments() - 1) {
				g2.drawLine((int)current_position, START_Y - 10, (int)current_position, START_Y + 10);
				g2.drawLine((int)current_position, START_Y + 40, (int)current_position, START_Y + 60);
			}
		}
		
		// Last station
		g2.drawLine(START_X + LENGTH, START_Y - 10, START_X + LENGTH, START_Y + 10);
		g2.drawLine(START_X + LENGTH, START_Y + 40, START_X + LENGTH, START_Y + 60);
	}

	private void printTrains(Graphics2D g2) {
		g2.setStroke(new BasicStroke(6));
		for (Train train : trains) {
			g2.setFont(new Font("Dialog", Font.PLAIN, 20));
			int pos = train.getPosition();
			double cur_l = (((double) pos/((double) line.getLength()-1))*((double)LENGTH-1));
			
			int way = train.getWay();
			switch(way) {
			case 0:
				g2.drawString("Train" + train.getId(), (int) cur_l, START_Y - 25);
				g2.drawLine(START_X + (int) cur_l, START_Y - 5, START_X + (int) cur_l, START_Y + 5);
				break;
			case 1:
				g2.drawString("Train" + train.getId(), (int) cur_l, START_Y + 75);
				g2.drawLine(START_X + (int) cur_l, START_Y + 45, START_X + (int) cur_l, START_Y + 55);

				break;
			}
		}
	}
	
	public void addTrain(Train train) {
		trains.add(train);
		System.out.println("Train " + train.getId() + " added to dashboard (at station " + train.getCurrentStation().getId() + ", way " + train.getWay() + ")");
		train.start();
	}
	
	public Line getLine() {
		return line;
	}

	public ArrayList<Train> getTrains() {
		return trains;
	}
	
	public void refreshTrains() {
		for (Train train : trains) {
			if(train.hasArrived()) {
				trains.remove(train);
			}
		}
	}
}