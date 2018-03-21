package simulation;

import line_management.Train;
import line_management.Line;
import line_management.Segment;
import line_management.Canton;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import line_management.Builder;

public class Dashboard  extends JPanel{
	private Line line;
	public List<Train> trains = new ArrayList<Train>();
	private static final int START_X = 50;
	private static final int LENGHT = 1000;
	private static final int START_Y = 150;


	public Dashboard(){
		Builder builder=  new Builder(true);
		builder.build(6);
		line = builder.getLine();
	}

	

		public void addTrain(Train train) {
			trains.add(train);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			printLine(g2);
			printTrains(g2);
		}

		private void printLine(Graphics2D g2) {
			g2.setStroke(new BasicStroke(8));
			double cur_l = START_X;
			int i = 0;
			g2.drawString("Canton 0" + " " + line.getStationAtPosition(0).getCapacity(), START_X, START_Y + 30);
			g2.drawLine(START_X, START_Y, START_X + LENGHT -1 , START_Y);
			g2.drawLine(START_X, START_Y + 50, START_X + LENGHT - 1, START_Y + 50);
			for (Segment segment : line.getSegments()) {
				int prev_int = (int) cur_l;
				cur_l = (((double) segment.getLength()/((double) line.getLength()-1))*((double)LENGHT-1)) + cur_l;
				int cur_int = (int) cur_l;
				//System.out.println("station" + i + " : " + "total l = " + line.getLength() + " segment lenght = " + segment.getLength() + " cur lenght = " + cur_l);			
				int n = segment.getId() +1;
				g2.drawString("Station " + n + " " + line.getStationAtPosition(segment.getEndPoint()+1).getCapacity(), cur_int, START_Y + 30);
				g2.drawLine(prev_int, START_Y - 10, prev_int, START_Y + 10);
				g2.drawLine(cur_int, START_Y - 10, cur_int, START_Y + 10);

				
				g2.drawLine(prev_int, START_Y + 40, prev_int, START_Y + 60);
				g2.drawLine(cur_int, START_Y + 40, cur_int, START_Y + 60);

				i++;

			}
		}

		private void printTrains(Graphics2D g2) {
			g2.setStroke(new BasicStroke(6));
			for (Train train : trains) {
				if(train.isArrived()) {
					System.out.println("I remove train " + train.getId() + " from trains") ;
					trains.remove(train);
				}

				g2.setFont(new Font("Dialog", Font.PLAIN, 20));
				int pos = train.getPosition();
				double cur_l = (((double) pos/((double) line.getLength()-1))*((double)LENGHT-1));
				
				System.out.println("Train" + train.getId() + " : " + "pos = " + train.getPosition() + " proportional = " + START_X + cur_l);			

				
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

		public Line getLine() {
			return line;
		}

		public void setLine(Line line) {
			this.line = line;
		}

		public List<Train> getTrains() {
			return trains;
		}
		
		public void refresh() {
			for (Train train : trains) {
				if(train.isArrived()) {
					System.out.println("I remove train " + train.getId() + " from trains") ;
					trains.remove(train);
				}
			}
		}

		public void setTrains(List<Train> trains) {
			this.trains = trains;
		}
}