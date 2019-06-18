package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.model.Edge;
import com.szberko.ditributedtracing.model.Node;

import java.util.function.Predicate;

/**
 * 10
 */
public class MeasureNoOfTracesWithLatencyLimit implements Measurement{
    private int hops;
    private int numberOfRoutes;
    private int currentLatency;
    private Node currentNode;
    private final Node destinationNode;
    private final Predicate<Integer> latencyPredicate;

    private MeasureNoOfTracesWithLatencyLimit(final Node currentNode,
                                              final Node destinationNode,
                                              final Predicate<Integer> latencyPredicate) {
        this.hops = 0;
        this.numberOfRoutes = 0;
        this.currentNode = currentNode;
        this.destinationNode = destinationNode;
        this.currentLatency = 0;
        this.latencyPredicate = latencyPredicate;
    }

    public static int calc(final Node currentNode,
                               final Node destinationNode,
                               final Predicate<Integer> latencyPredicate){
        final MeasureNoOfTracesWithLatencyLimit measureNoOfTracesWithLatencyLimit = new MeasureNoOfTracesWithLatencyLimit(currentNode, destinationNode, latencyPredicate);
        measureNoOfTracesWithLatencyLimit.getNumberOfDifferentTracesWithAnAverageLatency();
        return measureNoOfTracesWithLatencyLimit.getNumberOfRoutes();
    }

    private void getNumberOfDifferentTracesWithAnAverageLatency(){
        if(currentNode.equals(destinationNode) && latencyPredicate.test(currentLatency) && hops != 0) {
            numberOfRoutes++;
        }

        if(latencyPredicate.negate().test(currentLatency)){
            return;
        }

        for(Edge edge : currentNode.getOutgoingEdges()){
            this.hops++;
            currentLatency += edge.getLatency();
            currentNode = edge.getEndingNode();
            getNumberOfDifferentTracesWithAnAverageLatency();
            currentLatency -= edge.getLatency();
        }
    }

    private int getNumberOfRoutes(){
        return numberOfRoutes;
    }
}
