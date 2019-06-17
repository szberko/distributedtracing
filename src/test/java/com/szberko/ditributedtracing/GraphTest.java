package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.exception.NoSuchTrace;
import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.graph;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphTest {

    /**
     * 1
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_ABC_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("B"),
                new Node("C")), equalTo(9));
    }

    /**
     * 2
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AD_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D")), equalTo(5));
    }

    /**
     * 3
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_ADC_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("D"),
                new Node("C")), equalTo(13));
    }

    /**
     * 4
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AEBCD_thenReturnAvgLatency(){
        assertThat(graph.calculatedAvgLatency(
                new Node("A"),
                new Node("E"),
                new Node("B"),
                new Node("C"),
                new Node("D")), equalTo(22));
    }

    /**
     * 5
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AED_thenReturnAvgLatency(){
        assertThrows(NoSuchTrace.class, () -> graph.calculatedAvgLatency(
                new Node("A"),
                new Node("E"),
                new Node("D")));
    }

    /**
     * 6
     */
    @Test
    void givenGraph_whenCtoCWithMax3Hops_thenReturn2AsRouteCount(){
        assertThat(graph.getNumberOfRoutesWithSpecificHopsCriteria(
                "C",
                "C",
                hops -> hops <= 3,
                3), equalTo(2));
    }

    /**
     * 7
     */
    @Test
    void givenGraph_whenAtoCWithExactly4Hops_thenReturn3AsRouteCount(){
        assertThat(graph.getNumberOfRoutesWithSpecificHopsCriteria(
                "A",
                "C",
                hops -> hops == 4,
                4), equalTo(3));
    }

    /**
     * 8
     */
    @Test
    void givenGraph_whenAtoCFindMinLatency_thenReturn9(){
        assertThat(graph.getLengthOfTheShortestTrace("A", "C"), equalTo(9));
    }

    /**
     * 9
     */
    @Test
    void givenGraph_whenBtoBFindMinLatency_thenReturn9(){
        assertThat(graph.getLengthOfTheShortestTrace("B", "B"), equalTo(9));
    }

    /**
     * 10
     */
    @Test
    void givenGraph_whenCtoCFindAllDifferentTracesWithin30Latency_thenReturn7Routes(){
        assertThat(graph.getNumberOfRoutes(
                "C",
                "C",
                latency -> latency < 30), equalTo(7));
    }
}