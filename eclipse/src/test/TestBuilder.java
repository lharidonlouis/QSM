package test;

import line_management.Builder;
import line_management.Line;

public class TestBuilder {

	public static void main(String[] args) {
		Builder builder = new Builder();
		builder.build(4);
		Line line = builder.getLine();
		System.out.println(line.getDescription());
	}

}
