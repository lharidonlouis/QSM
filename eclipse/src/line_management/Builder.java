package line_management;

import java.lang.Math;

public class Builder {
	private Line line;
	private boolean built;
	private static final int MIN_LENGTH = 100;
	private static final int MAX_LENGTH = 300;
	
	public Builder() {
		built = false;
	}
	
	public void build(int lineLength) {
		line = new Line(lineLength);
		long id = 0;
		int startseg = 0;
		int length;
		
		while(line.getUsedLength() < line.getLength()) {
			length = (int) (Math.random()*(MAX_LENGTH - MIN_LENGTH + 1)) + MIN_LENGTH;
			Segment segment = new Segment(startseg, length , line, id);
			
			try {
				line.addSegment(segment);
			} catch (SizeExceededException e) {
				length = line.getLength() - line.getUsedLength();
				segment = new Segment(line.getUsedLength(), length, line, id);
				try {
					line.addSegment(segment);
				} catch (SizeExceededException e1) {
					/* Length miscalculation */
				}
			}

			id++;
		}
	}
	
	public Line getLine() {
		return line;
	}
	
	public boolean isBuilt() {
		return built;
	}
}
