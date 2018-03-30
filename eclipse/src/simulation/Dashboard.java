package simulation;

import line_management.Train;
import line_management.Line;
import line_management.Segment;

import java.awt.BasicStroke;
import java.awt.Color;
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
	private static final int START_X = 75;
	private static final int LENGTH = 1300;
	private static final int START_Y = 150;
	private double ratio;

	public Dashboard(){
		Builder builder = new Builder();
		builder.build(6);
		line = builder.getLine();

		ratio = (double) LENGTH / (double)line.getLength();
	}

	@Override
	protected void paintComponent(Graphics g) {
		setBackground(Color.darkGray);  //Whatever color
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		printLine(g2);
		printTrains(g2);
	}

	private void printLine(Graphics2D g2) {
		Segment segment;
		
		g2.setColor(Color.CYAN);
		g2.setStroke(new BasicStroke(2));
		double current_position = START_X;
        g2.setColor(Color.LIGHT_GRAY);
		//g2.drawString("Station 0 ", (int)current_position - 30, START_Y + 105);
		
		//visitors
        g2.setColor(Color.MAGENTA);
		int iteratorx = 0;
		int iteratory = 0;
		for(int i = 0; i<line.getStationAtPosition(0).getPassengers().size(); i++) {
			if(iteratorx == 20) {
				iteratorx = 0;
				iteratory = iteratory + 8;
			}
			switch(line.getStationAtPosition(0).getPassengers().get(i).getType()) {
			case 0:
				g2.setColor(Color.MAGENTA);
				drawCircleByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
			break;
			case 1:
				drawCircleByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
			break;
			case 2:
				drawLosByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
			break;
			}
			iteratorx = iteratorx + 4;
		}
        g2.setColor(Color.WHITE);
		for(int i = 0; i<(line.getStationAtPosition(0).getCapacity() - line.getStationAtPosition(0).getPassengers().size()); i++) {
			if(iteratorx == 20) {
				iteratorx = 0;
				iteratory = iteratory + 8;
			}
			drawCircleByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
			iteratorx = iteratorx + 4;
		}


		g2.setColor(Color.CYAN);

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
			
			//g2.setColor(Color.LIGHT_GRAY);
			//g2.drawString("Station " + n, (int)current_position - 30, START_Y + 105);
	        g2.setColor(Color.MAGENTA);
			//visitors
			iteratorx = 0;
			iteratory = 0;
			for(int i = 0; i<line.getStationAtPosition(ct+segment.getLength()+1).getPassengers().size(); i++) {
				if(iteratorx == 20) {
					iteratorx = 0;
					iteratory = iteratory + 8;
				}
				switch(line.getStationAtPosition(ct+segment.getLength()+1).getPassengers().get(i).getType()) {
				case 0:
					g2.setColor(Color.MAGENTA);
					drawCircleByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
				break;
				case 1:
					drawRectByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
				break;
				case 2:
					drawLosByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
				break;
				}

				iteratorx = iteratorx + 4;
			}
	        g2.setColor(Color.WHITE);
	        for(int i = 0; i< ( line.getStationAtPosition(ct+segment.getLength()+1).getCapacity() - line.getStationAtPosition(ct+segment.getLength()+1).getPassengers().size()); i++) {
				if(iteratorx == 20) {
					iteratorx = 0;
					iteratory = iteratory + 8;
				}
				drawCircleByCenter(g2, (int)current_position - 10 + iteratorx, START_Y + 110 + iteratory, 2);
				iteratorx = iteratorx + 4;
			}
			
			
			ct = ct + segment.getLength() + 1;
			
			// Stations
			if (segmentId != line.getNbSegments() - 1) {
				//g2.drawLine((int)current_position, START_Y - 10, (int)current_position, START_Y + 10);
				//g2.drawLine((int)current_position, START_Y + 10, (int)current_position, START_Y + 30);
		         //drawCircleByCenter(g2, (int)current_position, START_Y + 5, 10);
		         //drawCircleByCenter(g2, (int)current_position, START_Y + 25, 10);
				g2.setColor(Color.CYAN);
				g2.drawLine((int)current_position, START_Y + -10, (int)current_position, START_Y + 90);

			}
			
			
		}
		
		// Last station
        g2.setColor(Color.CYAN);
		g2.drawLine(START_X + LENGTH, START_Y - 10, START_X + LENGTH, START_Y + 10);
		g2.drawLine(START_X + LENGTH, START_Y + 10, START_X + LENGTH, START_Y + 90);
	}

	private void printTrains(Graphics2D g2) {
		g2.setColor(Color.CYAN);
		int iteratorx = 0;
		int iteratory = 0;
		g2.setStroke(new BasicStroke(2));
		for (Train train : trains) {
			g2.setFont(new Font("Dialog", Font.PLAIN, 20));
			int pos = train.getPosition();
			double cur_l = (((double) pos/((double) line.getLength()-1))*((double)LENGTH-1));
			
			int way = train.getWay();
			switch(way) {
			case 0:
				//g2.drawString("Train" + train.getId() + " " + train.getPassengers().size() + "/" + train.getCapacity(), (int) cur_l, START_Y - 25);
				//g2.drawLine(START_X + (int) cur_l - 5 , START_Y - 5, START_X + (int) cur_l  +5, START_Y + 5);
				g2.setColor(Color.CYAN);
				g2.drawRect(START_X + (int) cur_l - 30, START_Y +1, 60, 18);
			    g2.fillRect(START_X + (int) cur_l - 30, START_Y +1, 60, 18);
		        g2.setColor(Color.MAGENTA);
				//visitors
		        iteratorx = 0;
		        iteratory = 0;
				for(int i = 0; i< train.getPassengers().size(); i++) {
					if(iteratorx == 56) {
						iteratorx = 0;
						iteratory = iteratory + 4;
					}
					switch(train.getPassengers().get(i).getType()) {
					case 0:
						g2.setColor(Color.MAGENTA);
						drawCircleByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 4 + iteratory, 2);
					break;
					case 1:
						drawRectByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 4 + iteratory, 2);
					break;
					case 2:
						drawLosByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 4 + iteratory, 2);
					break;
					}
					iteratorx = iteratorx + 4;
				}
		        g2.setColor(Color.WHITE);
		        for(int i = 0; i< ( train.getCapacity() - train.getPassengers().size()); i++) {
					if(iteratorx == 56) {
						iteratorx = 0;
						iteratory = iteratory +4;
					}
					drawCircleByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 4 + iteratory, 2);
					iteratorx = iteratorx + 4;
				}

				break;
			case 1:
				g2.setColor(Color.CYAN);
				//g2.drawString("Train" + train.getId() + " " + train.getPassengers().size() + "/" + train.getCapacity(), (int) cur_l, START_Y + 55);
				//g2.drawLine(START_X + (int) cur_l + 5, START_Y + 15, START_X + (int) cur_l - 5, START_Y + 25);
				g2.drawRect(START_X + (int) cur_l - 30 , START_Y + 60, 60, 18);
			    g2.fillRect(START_X + (int) cur_l - 30 , START_Y + 60, 60, 18);
		        g2.setColor(Color.MAGENTA);
				//visitors
				iteratorx = 0;
				iteratory = 0;
				for(int i = 0; i< train.getPassengers().size(); i++) {
					if(iteratorx == 56) {
						iteratorx = 0;
						iteratory = iteratory + 4;
					}
					switch(train.getPassengers().get(i).getType()) {
						case 0:
							g2.setColor(Color.MAGENTA);
							drawCircleByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 63 + iteratory, 2);
						break;
						case 1:
							drawRectByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 63 + iteratory, 2);
						break;
						case 2:
							drawLosByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 63 + iteratory, 2);
						break;
					}
					iteratorx = iteratorx + 4;
				}
		        g2.setColor(Color.WHITE);
		        for(int i = 0; i< ( train.getCapacity() - train.getPassengers().size()); i++) {
					if(iteratorx == 56) {
						iteratorx = 0;
						iteratory = iteratory + 4;
					}
					drawCircleByCenter(g2, START_X + (int) cur_l - 26 + iteratorx, START_Y + 63 + iteratory, 2);
					iteratorx = iteratorx + 4;
				}

			    
				break;
			}
		}
	}
	
    void drawCircleByCenter(Graphics g, int x, int y, int radius){
        g.drawOval(x-radius, y-(radius), radius, radius);
    }
    void drawRectByCenter(Graphics g, int x, int y, int radius){
		g.setColor(Color.RED);
        g.drawRect(x-radius, y-(radius), radius, radius);
    }
    void drawLosByCenter(Graphics g, int x, int y, int radius){
		g.setColor(Color.YELLOW);
		int[] cordx = new int[] {x-2*(radius/2), x-(radius/2), x, x-(radius/2)} ;
	    	int[] cordy = new int[] {y-(radius/2), y-2*(radius/2), y-(radius/2), y} ;
	    	g.drawPolygon(cordx, cordy, 4);
    }
	
	
	public void addTrain(Train train) {
		trains.add(train);
		//System.out.println("Train " + train.getId() + " added to dashboard (at station " + train.getCurrentStation().getId() + ", way " + train.getWay() + ")");
		train.getCurrentStation().pickPassengers(train);
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

