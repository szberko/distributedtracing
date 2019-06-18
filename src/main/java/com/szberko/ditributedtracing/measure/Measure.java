package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Measure {

    private final Graph graph;

    public Measure(Graph graph) {
        this.graph = graph;
    }

    public String calculatedAvgLatency(final String... nodes){
        List<Node> listOfNodes = Stream.of(nodes)
                .map(nodeName -> graph.getNodes().get(nodeName))
                .collect(Collectors.toList());
        return MeasureAvgLatency.calc(listOfNodes);
    }

    public int getNumberOfRoutesWithSpecificHopsCriteria(final String startingNodeName,
                                                         final String endingNodeName,
                                                         final Predicate<Integer> hopsPredicate){
        return MeasureNoOfTracesWithHopsLimit.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                hopsPredicate,
                graph.getHopsLimit()
        );
    }

    public int getLengthOfTheShortestTrace(final String startingNodeName,
                                           final String endingNodeName){
        return MeasureLatencyOnShortestTrace.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                graph.getHopsLimit()
        );
    }

    public int getNumberOfRoutes(final String startingNodeName,
                                 final String endingNodeName,
                                 final Predicate<Integer> latencyPredicate) {
        return MeasureNoOfTracesWithLatencyLimit.calc(
                graph.getNodes().get(startingNodeName),
                graph.getNodes().get(endingNodeName),
                latencyPredicate
        );
    }
}
