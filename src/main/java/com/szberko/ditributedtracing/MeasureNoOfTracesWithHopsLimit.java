package com.szberko.ditributedtracing;

import java.util.function.Predicate;

public class MeasureNoOfTracesWithHopsLimit implements Measurement{

    private int hops;
    private int result;
    private Node currentNode;
    private final Node destinationNode;
    private final Predicate<Integer> hopsPredicate;
    private final Integer hopsLimit;

    private MeasureNoOfTracesWithHopsLimit(final Node currentNode,
                                          final Node destinationNode,
                                          final Predicate<Integer> hopsPredicate,
                                          final Integer hopsLimit) {
        this.hops = 0;
        this.result = 0;
        this.currentNode = currentNode;
        this.destinationNode = destinationNode;
        this.hopsPredicate = hopsPredicate;
        this.hopsLimit = hopsLimit;
    }

    public static Integer calc(final Node currentNode,
                            final Node destinationNode,
                            final Predicate<Integer> hopsPredicate,
                            final Integer hopsLimit){
        MeasureNoOfTracesWithHopsLimit something = new MeasureNoOfTracesWithHopsLimit(currentNode, destinationNode, hopsPredicate, hopsLimit);
        something.goToTheNextOne();
        return something.getResult();
    }

    private void goToTheNextOne(){
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
            this.currentNode = edge.getEndingNode();
            goToTheNextOne();
        }
        hops--;
    }

    private int getResult(){
        return result;
    }
}
