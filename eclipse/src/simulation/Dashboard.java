package simulation;

import line_management.Train;
import line_management.Line;
import line_management.Segment;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import line_management.Builder;

public class Dashboard  extends JPanel{
	private static final long serialVersionUID = 1L;
	private Line line;
	public ArrayList<Train> trains = new ArrayList<Train>();
	private static final int START_X = 50;
	private static final int LENGTH = 1200;
	private static final int START_Y = 150;
	private double ratio;

	public Dashboard(){
		Builder builder = new Builder();
		builder.build(8);
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
		
		g2.setStroke(new BasicStroke(2));
		double current_position = START_X;
		g2.drawString("Station 0", START_X - 20, START_Y + 80);
		
		g2.drawLine(START_X, START_Y, START_X + LENGTH, START_Y);
		g2.drawLine(START_X, START_Y + 20, START_X + LENGTH, START_Y + 20);
		
		
		g2.drawLine(START_X, START_Y + 60, START_X + LENGTH, START_Y + 60);
		g2.drawLine(START_X, START_Y + 80, START_X + LENGTH, START_Y + 80);

		
		// Station 0, way 0
		g2.drawLine(START_X, START_Y - 10, START_X, START_Y + 10);
		// Station 0, way 1
		g2.drawLine(START_X, START_Y + 10, START_X, START_Y + 90);
		
		//counter
		int ct = 0;
		
		for (int segmentId = 0; segmentId < line.getNbSegments(); segmentId++) {
			segment = line.getSegments().get(segmentId);
			
			current_position += (double)segment.getLength() * ratio;
			
			int n = segment.getId() + 1;
			
			g2.drawString("Station " + n + " " + line.getStationAtPosition(ct+segment.getLength()+1).getPassengers().size() + "/" + line.getStationAtPosition(ct+segment.getLength()+1).getCapacity(), (int)current_position - 20, START_Y + 80);
			ct = ct + segment.getLength() + 1;
			
			// Stations
			if (segmentId != line.getNbSegments() - 1) {
				//g2.drawLine((int)current_position, START_Y - 10, (int)current_position, START_Y + 10);
				//g2.drawLine((int)current_position, START_Y + 10, (int)current_position, START_Y + 30);
		         //drawCircleByCenter(g2, (int)current_position, START_Y + 5, 10);
		         //drawCircleByCenter(g2, (int)current_position, START_Y + 25, 10);
				g2.drawLine((int)current_position, START_Y + -10, (int)current_position, START_Y + 90);

			}
		}
		
		// Last station
		g2.drawLine(START_X + LENGTH, START_Y - 10, START_X + LENGTH, START_Y + 10);
		g2.drawLine(START_X + LENGTH, START_Y + 10, START_X + LENGTH, START_Y + 90);
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
				g2.drawString("Train" + train.getId() + " " + train.getPassengers().size() + "/" + train.getCapacity(), (int) cur_l, START_Y - 25);
				//g2.drawLine(START_X + (int) cur_l - 5 , START_Y - 5, START_X + (int) cur_l  +5, START_Y + 5);
				g2.drawRect(START_X + (int) cur_l - 15, START_Y +1, 30, 18);
				break;
			case 1:
				g2.drawString("Train" + train.getId() + " " + train.getPassengers().size() + "/" + train.getCapacity(), (int) cur_l, START_Y + 55);
				//g2.drawLine(START_X + (int) cur_l + 5, START_Y + 15, START_X + (int) cur_l - 5, START_Y + 25);
				g2.drawRect(START_X + (int) cur_l - 15 , START_Y + 59, 30, 18);

				break;
			}
		}
	}
	
    void drawCircleByCenter(Graphics g, int x, int y, int radius){
        //g.setColor(Color.LIGHT_GRAY);
        g.drawOval(x-radius, y-(radius), radius, radius);
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
		Iterator<Train> iter = trains.iterator();

		while (iter.hasNext()) {
		    Train train = iter.next();

		    if (train.hasArrived())
		        iter.remove();
		}
		
		/*for (Train train : trains) {
			if(train.hasArrived()) {
				trains.remove(train);
			}
		}*/
	}
	
}

