package com.szberko.ditributedtracing;

import java.util.ArrayList;
import java.util.List;

public class MeasureLatencyOnShortestTrace {

    List<Integer> latencies = new ArrayList<>();
    int currentLatency = 0;

    private int hops;
    private int result;
    private final Node destinationNode;

    public MeasureLatencyOnShortestTrace(final Node destinationNode) {
        this.hops = 0;
        this.destinationNode = destinationNode;
    }


    public void findShortestTrace(final Node currentNode, final Integer hopsLimit){
        if(currentNode.equals(destinationNode) && hops != 0){
            latencies.add(currentLatency);
            hops--;
            return;
        }

        if(hops > hopsLimit){
            hops--;
            return;
        }

        for(Edge edge : currentNode.getOutgoingEdges()){
            this.hops++;
            currentLatency += edge.getLatency();
            findShortestTrace(edge.getEndingNode(), hopsLimit);
            currentLatency -= edge.getLatency();
        }
        hops--;
    }

    public int getMinLatency(){
        return latencies.stream().min(Integer::compare).get();
    }
}
