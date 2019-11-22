package visualization;

import java.util.ArrayList;

public class EdgeDrawTest {

	public static void main(String[] args) {

		EdgeDraw graphDraw = new EdgeDraw("Edge Drawing");		
		
		double edgeLength = 197.111542;
		ArrayList<Double> objsDist = new ArrayList<Double>();
		 objsDist.add(146.8353093215);
		 objsDist.add(71.7650472902 );
		 objsDist.add(155.3906581473);
		 objsDist.add(188.118770251 );
		 objsDist.add(112.8620358733);
		 objsDist.add(95.0445518304 );
		 objsDist.add(170.7116793299);
		 objsDist.add(21.3247297498 );
		 objsDist.add(5.8304718743  );
		 objsDist.add(43.659788824  );
		 objsDist.add(83.3131021632 );
		 objsDist.add(35.287859517  );
		 objsDist.add(179.173950026 );
		 objsDist.add(163.9745908836);
		 objsDist.add(134.6058793083);
		 objsDist.add(13.7347966018 );
		 objsDist.add(52.5081678932 );
		 objsDist.add(27.9776965111 );
		 objsDist.add(105.5411703943);
		 objsDist.add(63.3600481409 );
		 objsDist.add(194.6708780932);
		 objsDist.add(124.0083777337);
		
		graphDraw.drawEdgeLine(edgeLength, objsDist);
		
		graphDraw.setSize(1000, 500);
		graphDraw.setVisible(true);

	}

}
