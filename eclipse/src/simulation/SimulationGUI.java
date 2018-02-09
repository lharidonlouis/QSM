package simulation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import line_management.Canton;
import line_management.Line;
import line_management.Train;
import line_management.TrainArrivedException;

public class SimulationGUI extends Application{
	private Dashboard dashboard=new Dashboard();
	public static final int DELAY = 100;
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
        
        Task task = new Task<Void>() {
            @Override public Void call() throws TrainArrivedException {
				int id = 0;
				while(true) {
					Canton[] terminus = new Canton[2];
					terminus[0] = line.getCantonAtPosition(0, 0);
					terminus[1] = line.getCantonAtPosition(line.getNbSegments()-1, 1);
					int position;
					//System.out.println("Sens " + 0 + " occuped : " +  terminus[0].isOccupied() );
					//System.out.println("Sens " + 1 + " occuped : " +  terminus[1].isOccupied() );
					for(int i =0; i<2; i++) {
						Train newtrain = null;
						if(!terminus[i].isOccupied()) {
							switch (i) {
								case 0:
										position = 0;
										newtrain = new Train(line, id, i, position, REGULAR_SPEED, line.getCantonAtPosition(0, 0));
								break;
								case 1:
										position = line.getLength();
										newtrain = new Train(line, id, i, position, REGULAR_SPEED, line.getCantonAtPosition(line.getNbSegments()-1, 1));
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
					try {
						Thread.sleep(DELAY);
						System.out.println(" ");
					} catch (InterruptedException e) {
						System.err.println(e.getMessage());
					}
				}
            }
        };
        
        
		dashboard.printRails(root);
		dashboard.printStations(root);
		dashboard.printStationsLines(root);

		
		/*Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	while(true) {
			    	int i=0;
					System.out.println("printing trains");
					for (Train train : dashboard.trains) {
						double pos = ((double)train.getPosition()/(double)line.getLength())*(double)1400;
						System.out.println("train" + i + " : " + "position = " + train.getPosition() + " position a lechelle : " + pos);			
						Rectangle train_gui=new Rectangle();	
						train_gui.setX((int)pos);
						train_gui.setY(420);
						train_gui.setWidth(20);
						train_gui.setHeight(10);
						train_gui.setFill(Color.DARKBLUE);
						i++;
						dashboard.trains_gui.add(train_gui);
						ObservableValue<Integer> obsInt = new SimpleIntegerProperty(train.getPosition()).asObject();
						train_gui.xProperty().bind(obsInt);
					    //if(!root.getChildren().contains(train_gui)) {
					   // 		root.getChildren().add(train_gui);
					    //}
					}
					try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
		    	}
		    }
		});*/

        
       
        new Thread(task).start();
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
	}	
	
}