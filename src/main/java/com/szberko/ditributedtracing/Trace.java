package com.szberko.ditributedtracing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trace {

    private int hops;
    private int result;
    private final Node destinationNode;

    public Trace(final Node destinationNode) {
        this.hops = 0;
        this.destinationNode = destinationNode;
    }

    public void goToTheNextOne(final Node currentNode, final Predicate<Integer> hopsPredicate, final Integer hopsLimit){
        if(currentNode.equals(destinationNode) && hopsPredicate.test(hops) && hops != 0){
            result++;
            hops--;
            return;
        }

        if(hops > hopsLimit){
            hops--;
            return;
        }

        for(Edge edge : currentNode.getOutgoingEdges()){
            this.hops++;
            goToTheNextOne(edge.getEndingNode(), hopsPredicate, hopsLimit);
        }
        hops--;
    }

    List<Integer> latencies = new ArrayList<>();
    int currentLatency = 0;

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

    private int numberOfRoutes;

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

    public int getMinLatency(){
        return latencies.stream().min(Integer::compare).get();
    }

    public int getResult() {
        return result;
    }
}
