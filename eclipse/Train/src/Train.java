package demothreading;

/**
 * @author tliu@u-cergy.fr
 */
public class Train extends Thread {
	private volatile int position = 0;
	private Line line;
	private Canton currentCanton;

	/**
	 * Distance per time unit.
	 */
	private int speed;
	private boolean hasArrived = false;

	public Train(Line line, Canton startCanton, int speed) {
		this.line = line;
		currentCanton = startCanton;
		currentCanton.enter(this);
		this.speed = speed;
	}

	public int getPosition() {
		return position;
	}

	public Canton getCurrentCanton() {
		return currentCanton;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setCurrentCanton(Canton currentCanton) {
		this.currentCanton = currentCanton;
	}

	@Override
	public void run() {
		while (!hasArrived) {
			try {
				sleep(SimulationGUI.TIME_UNIT);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			if (position + speed >= currentCanton.getEndPoint()) {
				try {
					Canton nextCanton = line.getCantonByPosition(position + speed);
					nextCanton.enter(this);
				} catch (TerminusException e) {
					hasArrived = true;
					position = line.getTotalLenght();
				}
			} else {
				updatePosition();
			}
		}
		currentCanton.exit();
	}

	@Override
	public String toString() {
		return "Train [speed=" + speed + "]";
	}

	public void updatePosition() {
		position += speed;
	}

}
