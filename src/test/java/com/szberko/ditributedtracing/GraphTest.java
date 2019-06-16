package com.szberko.ditributedtracing;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.graph;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private static final Graph graph = Provider.aGraph();

    @Test
    void givenGraph_whenGetOverallAvgLatency_ABC_thenReturnAvgLatency(){
        assertThat(9, equalTo(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("B"),
                new Node("C")))
        );
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_AD_thenReturnAvgLatency(){
        assertThat(5, equalTo(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D")))
        );
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_ADC_thenReturnAvgLatency(){
        assertThat(13, equalTo(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D"),
                new Node("C")))
        );
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_AEBCD_thenReturnAvgLatency(){
        assertThat(13, equalTo(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("E"),
                new Node("B"),
                new Node("C"),
                new Node("D")))
        );
    }

}