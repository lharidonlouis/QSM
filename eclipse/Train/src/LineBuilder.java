package demothreading;

/**
 * @author tliu@u-cergy.fr
 */
public class LineBuilder {
	private Line line;

	public void buildLine(int totalLength, int cantonLength) {
		line = new Line(totalLength);
		int id = 1;
		while (!line.isFull()) {
			line.addCanton(id, cantonLength);
			id++;
		}
	}

	public Line getBuiltLine() {
		return line;
	}
}
