package com.szberko.ditributedtracing.measure;

import com.szberko.ditributedtracing.exception.NoSuchTraceException;
import com.szberko.ditributedtracing.model.Node;

import java.util.List;

public class MeasureAvgLatency implements Measurement{

    private final List<Node> trace;

    private MeasureAvgLatency(final List<Node> trace) {
        this.trace = trace;
    }

    public static String calc(final List<Node> trace){
        final MeasureAvgLatency measureAvgLatency = new MeasureAvgLatency(trace);
        try {
            return String.valueOf(measureAvgLatency.calculatedAvgLatency());
        } catch (NoSuchTraceException e) {
            return "NO SUCH TRACE";
        }
    }

    public int calculatedAvgLatency() throws NoSuchTraceException {
        if(trace.size() <= 1){
            throw new IllegalArgumentException("There is no enough arguments");
        }

        int overallLatency = 0;
        for (int i = 1; i < trace.size(); i++){
            overallLatency += trace.get(i-1).getOutGoingEdgeWithSpecificDestination(trace.get(i))
                    .orElseThrow(() -> new NoSuchTraceException("No such trace"))
                    .getLatency();
        }
        return overallLatency;
    }

}
