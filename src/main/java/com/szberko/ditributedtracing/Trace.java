package com.szberko.ditributedtracing;

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
    
    public int getResult() {
        return result;
    }
}
