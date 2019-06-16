package com.szberko.ditributedtracing;

import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.graph;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class GraphTest {

//    private static final Graph graph = Provider.aGraph();

    @Test
    void givenGraph_whenGetOverallAvgLatency_ABC_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("B"),
                new Node("C")), equalTo(9));
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_AD_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D")), equalTo(5));
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_ADC_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D"),
                new Node("C")), equalTo(13));
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_AEBCD_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("E"),
                new Node("B"),
                new Node("C"),
                new Node("D")), equalTo(22));
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_AED_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("E"),
                new Node("D")), equalTo(13));
    }

    @Test
    void givenGraph_whenCtoCWithMax3Hops_thenReturn2AsRouteCount(){
        assertThat(graph.getNumberOfRoutesWithSpecificHopsCriteria(
                "C",
                "C",
                hops -> hops <= 3,
                3), equalTo(2));
    }

    @Test
    void givenGraph_whenAtoCWithExactly4Hops_thenReturn3AsRouteCount(){
        assertThat(graph.getNumberOfRoutesWithSpecificHopsCriteria(
                "A",
                "C",
                hops -> hops == 4,
                4), equalTo(3));
    }



}