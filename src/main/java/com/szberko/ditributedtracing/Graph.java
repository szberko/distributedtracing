package com.szberko.ditributedtracing;

import java.util.*;
import java.util.function.Predicate;

public class Graph {

    private final Map<String, Node> nodes;
    private final Map<String, Edge> edges;

    public Graph(Map<String, Node> nodes, Map<String, Edge> edges) {
        this.edges = edges;
        this.nodes = nodes;

        for(Map.Entry<String, Edge> edge : this.edges.entrySet()) {
            nodes.get(edge.getValue().getStartingNode().getName()).addOutGoingEdge(edge.getValue());
            nodes.get(edge.getValue().getEndingNode().getName()).addInComingEdge(edge.getValue());
        }
    }

    //TODO Improve me
    //TODO Add exception handling when there no such trace
    //TODO Add exception handling when there is no sufficient input parameters
    public int calculatedAvgLatency(Node... trace){
        int overallLatency = 0;
        for (int i = 1; i < trace.length; i++){
            overallLatency += edges.get(trace[i-1].getName() + trace[i].getName()).getLatency();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(nodes, graph.nodes) &&
                Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, edges);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
