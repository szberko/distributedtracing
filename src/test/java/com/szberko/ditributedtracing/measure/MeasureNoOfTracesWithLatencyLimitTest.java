package com.szberko.ditributedtracing.measure;

import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.providers.Provider.C;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class MeasureNoOfTracesWithLatencyLimitTest {

    @Test
    void givenStartAndEndNode_whenMeasureNoOfTracesWithLatencyLimit_thenReturnNoOfTraces(){
        assertThat(MeasureNoOfTracesWithLatencyLimit.calc(C, C, maxLatency -> maxLatency < 30), equalTo(7));
    }

    @Test
    void givenStartAndEndNodeWithZeroLatencyLimit_whenMeasureNoOfTracesWithLatencyLimit_thenReturnZeroTraces(){
        assertThat(MeasureNoOfTracesWithLatencyLimit.calc(C, C, maxLatency -> maxLatency < 0), equalTo(0));
    }

}