package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;

import java.util.function.Predicate;

public class Measure {

    private final Graph graph;

    public Measure(Graph graph) {
        this.graph = graph;
    }

    public String calculatedAvgLatency(final Node... nodes){
        return MeasureAvgLatency.calc(
                graph,
                nodes);
    }

    public int getNumberOfRoutesWithSpecificHopsCriteria(final String startingNodeName,
                                                         final String endingNodeName,
                                                         final Predicate<Integer> hopsPredicate,
                                                         final Integer hopsLimit){
        return MeasureNoOfTracesWithHopsLimit.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                hopsPredicate,
                hopsLimit
        );
    }

    public int getLengthOfTheShortestTrace(final String startingNodeName,
                                           final String endingNodeName){
        return MeasureLatencyOnShortestTrace.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                graph.getNodes().size()*2
        );
    }

    public int getNumberOfRoutes(final String startingNodeName,
                                 final String endingNodeName,
                                 final Predicate<Integer> latencyPredicate) {
        return MeasureNumberOfTraces.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                latencyPredicate,
                graph.getNodes().size()*2
        );
    }
}
