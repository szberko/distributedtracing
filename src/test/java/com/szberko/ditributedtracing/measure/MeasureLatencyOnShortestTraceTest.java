package com.szberko.ditributedtracing.measure;

import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.A;
import static com.szberko.ditributedtracing.Provider.C;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class MeasureLatencyOnShortestTraceTest {

    @Test
    void givenTwoConnectedNodes_whenMeasureLatencyOnShortestTrace_thenReturnLatency(){
        assertThat(MeasureLatencyOnShortestTrace.calc(A, C, 2), equalTo(9));
    }

    @Test
    void givenTwoConnectedNodesWithoutEnoughHops_whenMeasureLatencyOnShortestTrace_thenReturnZeroLatency(){
        assertThat(MeasureLatencyOnShortestTrace.calc(A, C, 1), equalTo(0));
    }

}