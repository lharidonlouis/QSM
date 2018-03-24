package line_management;

import line_management.Canton;
import line_management.Train;
import line_management.IncidentException;

public class Canton {
	private boolean occupied;
	private Incident incident = Incident.OK;
	private Train train;
    private Station station = null;

	
	public Canton() {
		occupied = false;
	}
	public void setIncident(Incident incident) {
		  this.incident = incident;
		    	}
	public Incident getIncident() {
			return incident;
		  	}
	
	/*public synchronized void enter(Train train) {
		if(occupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Canton oldcanton = train.getCurrentCanton();
		train.setCurrentCanton(this);
		train.updatePosition();
		oldcanton.exit();
		setOccupiedFalse();
	}
	*/
	public boolean hasStation () {
        return station != null;
    }
	// method for stopping train from canton
	
   	public void generateException(Incident incident) throws IncidentException {
   		if (occupied) {
   			setIncident(incident);
   			System.out.println("Incident state change :" + incident);
   			train.stop(incident);
    		}
    	}
   	
	public synchronized void enter(Train train) {
		if (occupied) {
			System.out.println(toString() + " occupied !");
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		Canton oldCanton = train.getCurrentCanton();
		train.setCurrentCanton(this);
		train.updatePosition();

		oldCanton.exit();
		setOccupiedTrue();
		System.out.println("Train " + train.getId() + " : Canton changed ");

	}
	
	
	public synchronized void exit() {
		setOccupiedFalse();
		notify();
		System.out.println("Canton freed !");
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupiedTrue() {
		this.occupied = true;
	}
	public void setOccupiedFalse() {
		this.occupied = false;
	}
}