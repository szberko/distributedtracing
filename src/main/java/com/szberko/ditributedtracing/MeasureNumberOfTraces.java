package com.szberko.ditributedtracing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MeasureNumberOfTraces {
    private int numberOfRoutes;
    private int hops;
    private int result;
    private final Node destinationNode;

    List<Integer> latencies = new ArrayList<>();
    int currentLatency = 0;

    public MeasureNumberOfTraces(final Node destinationNode) {
        this.hops = 0;
        this.destinationNode = destinationNode;
    }

    public void getNumberOfDifferentTracesWithAnAvarageLatency(final Node currentNode, final Predicate<Integer> latencyPredicate, final Integer hopsLimit){
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
            getNumberOfDifferentTracesWithAnAvarageLatency(edge.getEndingNode(), latencyPredicate, hopsLimit);
            currentLatency -= edge.getLatency();
        }
        hops--;
    }

    public int getNumberOfRoutes(){
        return numberOfRoutes;
    }
}
