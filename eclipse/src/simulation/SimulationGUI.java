package simulation;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import line_management.Canton;
import line_management.Line;
import line_management.Train;

public class SimulationGUI extends Application{
	private Dashboard dashboard=new Dashboard();
	public static final int DELAY = 500;
	public static final int REGULAR_SPEED = 2;
	private Line line;
	public static void main(String[] args) {
		Application.launch(SimulationGUI.class, args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("QSM");
		Group root=new Group();
		Scene scene = new Scene(root, 1920, 1080, Color.ALICEBLUE);
		
		line = dashboard.getLine();
		Train newtrain = new Train(line, 0, 0, REGULAR_SPEED, line.getCantonAtPosition(0, 0));
		newtrain.start();	
		Train newtrain1 = new Train(line, 1, line.getLength(), REGULAR_SPEED, line.getCantonAtPosition(line.getLength(), 1));
		newtrain1.start();

		dashboard.printRails(root);
		dashboard.printStations(root);
		dashboard.printStationsLines(root);
		dashboard.printTrains(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
 
      /*  	while(true) {
    			line = dashboard.getLine();
    			Canton[] firstCantons = new Canton[2];
    			firstCantons[0] = line.getSegments().get(0).getCanton(0);
    			firstCantons[1] = line.getSegments().get(line.getNbSegments()-1).getCanton(1);
    			int position;	
    			for (int i = 0; i < 2; i++) {
    				System.out.println("i = " + i + " occupied : "+ firstCantons[i].isOccupied());
    				if (!firstCantons[i].isOccupied()) {
    					if (i == 0)
    						position = 0;
    					else position = line.getLength();
    					Train newtrain = new Train(line, i, position, REGULAR_SPEED);
    					newtrain.start();	
    					//dashboard.addTrain(newtrain);
    					//HANDLE SPEED VARIATIONS 
    				}
    			}

    			try {
    				Thread.sleep(DELAY);
    			} catch (InterruptedException e) {
    				System.err.println(e.getMessage());
    			}
        	}*/

        
        
        
						
	}	
	
}