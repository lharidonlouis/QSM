package simulation;

import javax.swing.JFrame;

public class SimulationGUI extends JFrame {
	private SimulationPanel content=new SimulationPanel();
	
	public SimulationGUI(){
		JFrame window=new JFrame();
		window.setTitle("QSM");
		window.setSize(1000, 1000);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		//getContentPane().add(content);
	}

	public static void main(String[] args) {
		SimulationGUI test=new SimulationGUI();
	}
}