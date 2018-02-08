package simulation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

public class SimulationGUI extends Application {

	public static void main(String[] args) {
		Application.launch(SimulationGUI.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("QSM");
		Group root=new Group();
		Scene scene = new Scene(root, 800, 600, Color.ALICEBLUE);
        
		printRails(root);
		printStations(root);
		printStationsLines(root);
		
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public void printRails(Group window){
		Rectangle go=new Rectangle();
		Rectangle goback=new Rectangle();
		
		go.setX(50);
		go.setY(300);
		go.setWidth(700);
		go.setHeight(50);
		go.setFill(Color.ANTIQUEWHITE);
		go.setStroke(Color.BLACK);
		go.setStrokeWidth(2);
        go.setArcHeight(10);
        go.setArcWidth(10);
		
		goback.setX(50);
		goback.setY(400);
		goback.setWidth(700);
		goback.setHeight(50);
		goback.setFill(Color.ANTIQUEWHITE);
		goback.setStroke(Color.BLACK);
		goback.setStrokeWidth(2);
		goback.setArcHeight(10);
        goback.setArcWidth(10);
        
        window.getChildren().add(go);
        window.getChildren().add(goback);
	}
	
	public void printStations(Group window){
		Circle station1=new Circle();
		Circle station2=new Circle();
		Circle station3=new Circle();
		Circle station4=new Circle();
		Circle station5=new Circle();
		Circle station6=new Circle();
		Circle station7=new Circle();
		
		stationsSpecs(station1, 100);
		stationsSpecs(station2, 200);
		stationsSpecs(station3, 300);
		stationsSpecs(station4, 400);
		stationsSpecs(station5, 500);
		stationsSpecs(station6, 600);
		stationsSpecs(station7, 700);
		
		window.getChildren().add(station1);
		window.getChildren().add(station2);
		window.getChildren().add(station3);
		window.getChildren().add(station4);
		window.getChildren().add(station5);
		window.getChildren().add(station6);
		window.getChildren().add(station7);
	}
	
	public void printStationsLines(Group window){
		Line station1above=new Line(100, 300, 100, 350);
		Line station1under=new Line(100, 400, 100, 450);
		Line station2above=new Line(200, 300, 200, 350);
		Line station2under=new Line(200, 400, 200, 450);
		Line station3above=new Line(300, 300, 300, 350);
		Line station3under=new Line(300, 400, 300, 450);
		Line station4above=new Line(400, 300, 400, 350);
		Line station4under=new Line(400, 400, 400, 450);
		Line station5above=new Line(500, 300, 500, 350);
		Line station5under=new Line(500, 400, 500, 450);
		
		
		//station1.setRotate(0);
		
		window.getChildren().add(station1above);
		window.getChildren().add(station1under);
		window.getChildren().add(station2above);
		window.getChildren().add(station2under);
		window.getChildren().add(station3above);
		window.getChildren().add(station3under);
		window.getChildren().add(station4above);
		window.getChildren().add(station4under);
		window.getChildren().add(station5above);
		window.getChildren().add(station5under);
	}
	
	public void stationsSpecs(Circle station, int positionX){
		station.setCenterX(positionX);
		station.setCenterY(375);
		station.setRadius(20);
		station.setFill(Color.DARKTURQUOISE);
		station.setStroke(Color.DARKBLUE);
	}
}