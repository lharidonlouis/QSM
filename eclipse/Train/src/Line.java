package demothreading;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tliu@u-cergy.fr
 */
public class Line {
	private int totalLenght;
	private int usedLength = 0;

	private List<Canton> cantons = new ArrayList<Canton>();

	public Line(int totalLenght) {
		this.totalLenght = totalLenght;
	}

	public void addCanton(int id, int cantonLength) {
		Canton canton;
		if (usedLength + cantonLength <= totalLenght) {
			canton = new Canton(id, cantonLength, usedLength);
			usedLength += cantonLength;
		} else {
			canton = new Canton(id, totalLenght - usedLength, usedLength);
			usedLength = totalLenght;
		}
		cantons.add(canton);
	}

	public boolean isFull() {
		return usedLength == totalLenght;
	}

	public int getTotalLenght() {
		return totalLenght;
	}

	public int getUsedLength() {
		return usedLength;
	}

	public List<Canton> getCantons() {
		return cantons;
	}

	public Canton getCantonByPosition(int position) throws TerminusException {
		for (Canton canton : cantons) {
			if (canton.getEndPoint() > position) {
				return canton;
			}
		}
		throw new TerminusException();
	}

}
