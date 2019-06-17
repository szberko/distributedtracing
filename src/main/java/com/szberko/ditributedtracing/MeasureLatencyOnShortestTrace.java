package com.szberko.ditributedtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 8
 * 9
 */
public class MeasureLatencyOnShortestTrace {

    private int hops;
    private Node currentNode;
    private final Node destinationNode;
    private List<Integer> latencies;
    private int currentLatency;
    private final Integer hopsLimit;

    private MeasureLatencyOnShortestTrace(final Node currentNode,
                                         final Node destinationNode,
                                         final Integer hopsLimit) {
        this.hops = 0;
        this.currentNode = currentNode;
        this.currentLatency = 0;
        this.destinationNode = destinationNode;
        this.latencies = new ArrayList<>();
        this.hopsLimit = hopsLimit;
    }

    public static Integer calc(final Node currentNode,
                               final Node destinationNode,
                               final Integer hopsLimit){
        MeasureLatencyOnShortestTrace measureLatencyOnShortestTrace = new MeasureLatencyOnShortestTrace(currentNode, destinationNode, hopsLimit);
        measureLatencyOnShortestTrace.findShortestTrace();
        return measureLatencyOnShortestTrace.getMinLatency();
    }


    private void findShortestTrace(){
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
            currentNode = edge.getEndingNode();
            findShortestTrace();
            currentLatency -= edge.getLatency();
        }
        hops--;
    }

    private int getMinLatency(){
        return latencies.stream().min(Integer::compare).get();
    }
}
