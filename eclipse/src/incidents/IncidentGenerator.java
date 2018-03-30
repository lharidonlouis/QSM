package incidents;

import java.util.Random;
import line_management.Line;

public class IncidentGenerator {
	public Incident createIncident(Line line) {
		Incident result;
		Random r = new Random();
		int type = r.nextInt(2);
		int index, way;
		
		way = r.nextInt(2);
		
		if (type == 0) {
			index = r.nextInt(line.getNbStations());
			result = new StationIncident(line, line.getStations().get(index), way);
		}
		else {
			index = r.nextInt(line.getNbSegments());
			result = new CantonIncident(line, line.getSegments().get(index).getCanton(way), way);
		}
		
		return result;
	}
}