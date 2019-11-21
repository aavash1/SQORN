package visualization;

import java.util.ArrayList;

public class EdgeDrawTest {

	public static void main(String[] args) {

		EdgeDraw graphDraw = new EdgeDraw("Edge Drawing");		
		
		double edgeLength = 61.679562;
		ArrayList<Double> objsDist = new ArrayList<Double>();
		objsDist.add(11.4704378005);
		objsDist.add(57.7300710786);
		objsDist.add(35.9544393279);
		objsDist.add(44.7005220113);
		objsDist.add(28.0895263536);
		objsDist.add(0.4496060918);
		objsDist.add(20.4938543799);
		
		graphDraw.drawEdgeLine(edgeLength, objsDist);
		
		graphDraw.setSize(1000, 300);
		graphDraw.setVisible(true);

	}

}
