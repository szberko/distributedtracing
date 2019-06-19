package com.instana.ditributedtracing.measure;

import org.junit.jupiter.api.Test;

import static com.instana.ditributedtracing.providers.Provider.A;
import static com.instana.ditributedtracing.providers.Provider.C;
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