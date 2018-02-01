package line_management;

import java.util.List;

public class Station {
	private long id;
	private String name;
	private String type;
	private Line line;
	private int capacity;
	private List<Passenger> passengers;
	
	public Station() {
		
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
