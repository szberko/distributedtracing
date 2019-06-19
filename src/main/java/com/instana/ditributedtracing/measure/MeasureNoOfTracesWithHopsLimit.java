package com.instana.ditributedtracing.measure;

import com.instana.ditributedtracing.model.Edge;
import com.instana.ditributedtracing.model.Node;

import java.util.function.Predicate;

/**
 * 6
 * 7
 */
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
        final MeasureNoOfTracesWithHopsLimit measureNoOfTracesWithHopsLimit = new MeasureNoOfTracesWithHopsLimit(currentNode, destinationNode, hopsPredicate, hopsLimit);
        measureNoOfTracesWithHopsLimit.walkThrough();
        return measureNoOfTracesWithHopsLimit.getResult();
    }

    private void walkThrough(){
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
            walkThrough();
        }
        hops--;
    }

    private int getResult(){
        return result;
    }
}
