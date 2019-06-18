package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.exception.NoSuchTraceException;
import com.szberko.ditributedtracing.model.Graph;
import com.szberko.ditributedtracing.model.Node;

public class MeasureAvgLatency {

    private final Graph graph;
    private final Node[] trace;

    private MeasureAvgLatency(final Graph graph, Node... trace) {
        this.graph = graph;
        this.trace = trace;
    }

    public static String calc(final Graph graph,
                               final Node... trace){
        final MeasureAvgLatency measureAvgLatency = new MeasureAvgLatency(graph, trace);
        try {
            return String.valueOf(measureAvgLatency.calculatedAvgLatency());
        } catch (NoSuchTraceException e) {
            return "NO SUCH TRACE";
        }
    }

    public int calculatedAvgLatency() throws NoSuchTraceException {
        if(trace.length <= 1){
            throw new IllegalArgumentException("There is no enough arguments");
        }

//        return IntStream.range(1, trace.length)
//                .mapToObj(i -> graph.getNodes().get(trace[i-1].getName()).getOutGoingEdgeWithSpecificDestination(trace[i]))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(Edge::getLatency)
//                .mapToInt(Integer::intValue)
//                .sum();


        int overallLatency = 0;
        for (int i = 1; i < trace.length; i++){
            overallLatency += graph.getNodes().get(trace[i-1].getName()).getOutGoingEdgeWithSpecificDestination(trace[i])
                    .orElseThrow(() -> new NoSuchTraceException("No such trace"))
                    .getLatency();
        }
        return overallLatency;
    }

}
