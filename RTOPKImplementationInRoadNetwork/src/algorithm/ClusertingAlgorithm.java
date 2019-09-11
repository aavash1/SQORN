package algorithm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import framework.Graph;
import framework.Graph;
import framework.Node;

public class ClusertingAlgorithm {
	private ArrayList<List<Integer>> nodeSequences = new ArrayList<List<Integer>>();
	private ArrayList<Integer> vistitedNodes = new ArrayList<Integer>();
	private ArrayList<Integer> clearedNodes = new ArrayList<Integer>();

	private int setCounter = 0;

	public void clusterNodes(Graph gr) {
		for (Node nodeId : gr.getNodesWithInfo()) {
			if (!gr.isIntermediateNode(nodeId.getNodeId())) {
				vistitedNodes.add(nodeId.getNodeId());
				setCounter = gr.getAdjNodeIds(nodeId.getNodeId()).size();
				for (int i = 0; i < setCounter; i++) {
					while (setCounter != 0) {

						if (gr.isIntersectionNode(gr.getAdjNodeIds(nodeId.getNodeId()).get(i))
								|| (gr.isTerminalNode(gr.getAdjNodeIds(nodeId.getNodeId()).get(i)))) {
							List<Integer> nodeSeq = new ArrayList<Integer>();

							nodeSeq.add(nodeId.getNodeId());
							nodeSeq.add(gr.getAdjNodeIds(nodeId.getNodeId()).get(i));
							nodeSequences.add(nodeSeq);
							clearedNodes.add(gr.getAdjNodeIds(nodeId.getNodeId()).get(i));
							setCounter -= 1;

						} else {
							gr.getAdjNodeIds(gr.getAdjNodeIds(nodeId.getNodeId()).get(i));
							for (int adjNodes : gr.getAdjNodeIds(gr.getAdjNodeIds(nodeId.getNodeId()).get(i))) {
							if(gr.isIntermediateNode(adjNodes)) {
								while(!gr.getAdjNodeIds(adjNodes).isEmpty()) {
									
								}
							}

							}

						}

					}

				}

			}
		}
	}

}
