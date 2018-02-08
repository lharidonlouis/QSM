package simulation;

import line_management.Train;
import line_management.Line;
import line_management.Segment;
import line_management.Canton;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import line_management.Builder;

public class Dashboard {
	private Line line;
	private List<Train> trains = new ArrayList<Train>();
	private static final int START_X = 50;
	private static final int LENGHT = 1400;

	public Dashboard(){
		Builder builder=new Builder();
		builder.build(15);
		line = builder.getLine();
	}
	
	public void addTrain(Train train) {
		trains.add(train);
	}
	
	public void printRails(Group window){
		Rectangle railforward=new Rectangle();
		Rectangle railbackward=new Rectangle();
		
		railforward.setX(START_X);
		railforward.setY(300);
		railforward.setWidth(LENGHT);
		railforward.setHeight(START_X);
		railforward.setFill(Color.ANTIQUEWHITE);
		railforward.setStroke(Color.BLACK);
		railforward.setStrokeWidth(2);
		railforward.setArcHeight(10);
		railforward.setArcWidth(10);
		
		railbackward.setX(START_X);
		railbackward.setY(400);
		railbackward.setWidth(LENGHT);
		railbackward.setHeight(START_X);
		railbackward.setFill(Color.ANTIQUEWHITE);
		railbackward.setStroke(Color.BLACK);
		railbackward.setStrokeWidth(2);
		railbackward.setArcHeight(10);
		railbackward.setArcWidth(10);
        window.getChildren().add(railforward);
        window.getChildren().add(railbackward);
	}
	
	public void printStations(Group window){		
		List<Circle>  circles = new ArrayList<Circle>();
		double cur_l = START_X;
		int i = 0;
		Circle departure=new Circle();
		stationsSpecs(departure, (int)cur_l);
		circles.add(departure);
		for (Segment segment : line.getSegments()) {
			cur_l = (((double) segment.getLength()/(double) line.getLength())*(double)LENGHT) + cur_l; 
			System.out.println("station" + i + " : " + "total l = " + line.getLength() + " segment lenght = " + segment.getLength() + " cur lenght = " + cur_l);			
			Circle station=new Circle();
			stationsSpecs(station, (int)cur_l);
			circles.add(station);
			i++;
		}
		window.getChildren().addAll(circles);
	}
	
	public void printStationsLines(Group window){
		List<javafx.scene.shape.Line>  linesabove = new ArrayList<javafx.scene.shape.Line>();
		List<javafx.scene.shape.Line>  linesunder = new ArrayList<javafx.scene.shape.Line>();
		double cur_l = START_X;
		int i = 0;
		for (Segment segment : line.getSegments()) {
			cur_l = (((double) segment.getLength()/(double) line.getLength())*(double)LENGHT) + cur_l; 
			javafx.scene.shape.Line lineabove=new javafx.scene.shape.Line((int)cur_l, 300, (int)cur_l, 350);
			javafx.scene.shape.Line lineunder=new javafx.scene.shape.Line((int)cur_l, 400, (int)cur_l, 450);
			linesabove.add(lineabove);
			linesunder.add(lineunder);
			i++;
		}
		window.getChildren().addAll(linesabove);
		window.getChildren().addAll(linesunder);
	}
	
	public void printTrains(Group window){
		List<Rectangle> trains_gui = new ArrayList<Rectangle>();
		int i=0;
		for (Train train : trains) {
			double pos = ((double)train.getPosition()/(double)line.getLength())*(double)LENGHT;
			System.out.println("train" + i + " : " + "position = " + train.getPosition() + " position a lechelle : " + pos);			
			Rectangle train_gui=new Rectangle();	
			train_gui.setX((int)pos);
			train_gui.setY(420);
			train_gui.setWidth(20);
			train_gui.setHeight(10);
			train_gui.setFill(Color.DARKBLUE);
			i++;
		}
		window.getChildren().addAll(trains_gui);
	}
	
	public void stationsSpecs(Circle station, int positionX){
		station.setCenterX(positionX);
		station.setCenterY(375);
		station.setRadius(10);
		station.setFill(Color.DARKTURQUOISE);
		station.setStroke(Color.DARKBLUE);
	}
	
	Line getLine() {
		return this.line;
	}
}
