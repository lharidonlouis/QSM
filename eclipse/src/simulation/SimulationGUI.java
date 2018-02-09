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
import line_management.TrainArrivedException;

public class SimulationGUI extends Application{
	private Dashboard dashboard=new Dashboard();
	public static final int DELAY = 50;
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
		
		/*Train newtrain = new Train(line, 15, 0, 0, REGULAR_SPEED, line.getCantonAtPosition(0, 0));
		newtrain.start();	
		Train newtrain1 = new Train(line,124, 1, line.getLength(), REGULAR_SPEED, line.getCantonAtPosition(line.getLength(), 1));
		newtrain1.start();
		*/

		//dashboard.printTrains(root);
		dashboard.printRails(root);
		dashboard.printStations(root);
		dashboard.printStationsLines(root);
        
		primaryStage.setScene(scene);
        primaryStage.show();
        
        Task task = new Task<Void>() {
            @Override public Void call() throws TrainArrivedException {
				int id = 0;
				while(true) {
					Canton[] terminus = new Canton[2];
					terminus[0] = line.getCantonAtPosition(0, 0);
					terminus[1] = line.getCantonAtPosition(line.getNbSegments()-1, 1);
					int position;
					System.out.println("Sens " + 0 + " occuped : " +  terminus[0].isOccupied() );
					System.out.println("Sens " + 1 + " occuped : " +  terminus[1].isOccupied() );
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
							dashboard.addTrain(newtrain);
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
        new Thread(task).start();
        
	}	
	
}