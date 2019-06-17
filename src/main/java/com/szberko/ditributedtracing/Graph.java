package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.exception.NoSuchTrace;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Graph {

    private final Map<String, Node> nodes;

    public Graph(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public int calculatedAvgLatency(Node... trace){
        if(trace.length <= 1){
            throw new IllegalArgumentException("There is no enough arguments");
        }

//        return IntStream.range(1, trace.length)
//                .mapToObj(i -> nodes.get(trace[i-1].getName()).getOutGoingEdgeWithSpecificDestination(trace[i]))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(Edge::getLatency)
//                .mapToInt(Integer::intValue)
//                .sum();


        int overallLatency = 0;
        for (int i = 1; i < trace.length; i++){
            overallLatency += nodes.get(trace[i-1].getName()).getOutGoingEdgeWithSpecificDestination(trace[i])
                    .orElseThrow(() -> new NoSuchTrace("No such trace"))
                    .getLatency();
        }
        return overallLatency;
    }

    public int getNumberOfRoutesWithSpecificHopsCriteria(final String startingNodeName,
                                                         final String endingNodeName,
                                                         final Predicate<Integer> hopsPredicate,
                                                         final Integer hopsLimit){
        return MeasureNoOfTracesWithHopsLimit.calc(
                nodes.get(startingNodeName),
                nodes.get(endingNodeName),
                hopsPredicate,
                hopsLimit
        );
    }

    public int getLengthOfTheShortestTrace(final String startingNodeName,
                                           final String endingNodeName){
        return MeasureLatencyOnShortestTrace.calc(
                nodes.get(startingNodeName),
                nodes.get(endingNodeName),
                nodes.size()*2
        );
    }

    public int getNumberOfRoutes(final String startingNodeName,
                                 final String endingNodeName,
                                 final Predicate<Integer> latencyPredicate) {
        return MeasureNumberOfTraces.calc(
                nodes.get(startingNodeName),
                nodes.get(endingNodeName),
                latencyPredicate,
                nodes.size()*2
        );
    }
}
