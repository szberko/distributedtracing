package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.model.Edge;
import com.szberko.ditributedtracing.model.Node;

import java.util.function.Predicate;

/**
 * 10
 */
public class MeasureNumberOfTraces {
    private int hops;
    private int numberOfRoutes;
    private int currentLatency;
    private Node currentNode;
    private final Node destinationNode;
    private final Predicate<Integer> latencyPredicate;
    private final Integer hopsLimit;

    private MeasureNumberOfTraces(final Node currentNode,
                                 final Node destinationNode,
                                 final Predicate<Integer> latencyPredicate,
                                 final Integer hopsLimit) {
        this.hops = 0;
        this.numberOfRoutes = 0;
        this.currentNode = currentNode;
        this.destinationNode = destinationNode;
        this.currentLatency = 0;
        this.latencyPredicate = latencyPredicate;
        this.hopsLimit = hopsLimit;
    }

    public static Integer calc(final Node currentNode,
                               final Node destinationNode,
                               final Predicate<Integer> latencyPredicate,
                               final Integer hopsLimit){
        final MeasureNumberOfTraces measureNumberOfTraces = new MeasureNumberOfTraces(currentNode, destinationNode, latencyPredicate, hopsLimit);
        measureNumberOfTraces.getNumberOfDifferentTracesWithAnAverageLatency();
        return measureNumberOfTraces.getNumberOfRoutes();
    }

    private void getNumberOfDifferentTracesWithAnAverageLatency(){
        if(currentNode.equals(destinationNode) && latencyPredicate.test(currentLatency) && hops != 0) {

            numberOfRoutes++;

            if(latencyPredicate.negate().test(currentLatency)){
                hops--;
                return;
            }
        }

        if(hops > hopsLimit){
            hops--;
            return;
        }

        for(Edge edge : currentNode.getOutgoingEdges()){
            this.hops++;
            currentLatency += edge.getLatency();
            currentNode = edge.getEndingNode();
            getNumberOfDifferentTracesWithAnAverageLatency();
            currentLatency -= edge.getLatency();
        }
        hops--;
    }

    private int getNumberOfRoutes(){
        return numberOfRoutes;
    }
}
