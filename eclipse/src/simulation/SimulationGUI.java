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
	private Dashboard dashboard=new Dashboard();

	public static void main(String[] args) {
		Application.launch(SimulationGUI.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("QSM");
		Group root=new Group();
		Scene scene = new Scene(root, 800, 600, Color.ALICEBLUE);
        
		dashboard.printRails(root);
		dashboard.printStations(root);
		dashboard.printStationsLines(root);
		dashboard.printTrains(root);
		
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
}