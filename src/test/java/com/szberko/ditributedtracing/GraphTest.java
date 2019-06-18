package com.szberko.ditributedtracing;

import com.szberko.ditributedtracing.exception.NoSuchTraceException;
import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.MEASURE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphTest {

    /**
     * 1
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_ABC_thenReturnAvgLatency(){
        assertThat(MEASURE.calculatedAvgLatency("A", "B", "C"), equalTo(9));
    }

    /**
     * 2
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AD_thenReturnAvgLatency(){
        assertThat(MEASURE.calculatedAvgLatency("A", "D"), equalTo(5));
    }

    /**
     * 3
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_ADC_thenReturnAvgLatency(){
        assertThat(MEASURE.calculatedAvgLatency("A", "D", "C"), equalTo(13));
    }

    /**
     * 4
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AEBCD_thenReturnAvgLatency(){
        assertThat(MEASURE.calculatedAvgLatency("A", "E", "B", "C", "D"), equalTo(22));
    }

    /**
     * 5
     */
    @Test
    void givenGraph_whenGetOverallAvgLatency_AED_thenReturnAvgLatency(){
        assertThrows(NoSuchTraceException.class, () -> MEASURE.calculatedAvgLatency("A", "E", "D"));
    }

    /**
     * 6
     */
    @Test
    void givenGraph_whenCtoCWithMax3Hops_thenReturn2AsRouteCount(){
        assertThat(MEASURE.getNumberOfRoutesWithSpecificHopsCriteria("C", "C",
                hops -> hops <= 3), equalTo(2));
    }

    /**
     * 7
     */
    @Test
    void givenGraph_whenAtoCWithExactly4Hops_thenReturn3AsRouteCount(){
        assertThat(MEASURE.getNumberOfRoutesWithSpecificHopsCriteria(
                "A",
                "C",
                hops -> hops == 4), equalTo(3));
    }

    /**
     * 8
     */
    @Test
    void givenGraph_whenAtoCFindMinLatency_thenReturn9(){
        assertThat(MEASURE.getLengthOfTheShortestTrace("A", "C"), equalTo(9));
    }

    /**
     * 9
     */
    @Test
    void givenGraph_whenBtoBFindMinLatency_thenReturn9(){
        assertThat(MEASURE.getLengthOfTheShortestTrace("B", "B"), equalTo(9));
    }

    /**
     * 10
     */
    @Test
    void givenGraph_whenCtoCFindAllDifferentTracesWithin30Latency_thenReturn7Routes(){
        assertThat(MEASURE.getNumberOfRoutes(
                "C",
                "C",
                latency -> latency < 30), equalTo(7));
    }

    @Test
    void givenGraph_whenGetOverallAvgLatency_A_thenThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> MEASURE.calculatedAvgLatency(
                "A"));
    }
}