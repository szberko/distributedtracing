package com.szberko.ditributedtracing.measure;

import org.junit.jupiter.api.Test;

import static com.szberko.ditributedtracing.Provider.C;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class MeasureNoOfTracesWithHopsLimitTest {

    @Test
    void givenStartAndFinalNode_whenMeasureNoOfTracesWithHopsLimit_thenReturnNumberOfTraces(){
        assertThat(MeasureNoOfTracesWithHopsLimit.calc(C, C, maxHops -> maxHops <= 3, 3), equalTo(2));
    }

    @Test
    void givenZeroHopsAsLimit_whenMeasureNoOfTracesWithHopsLimit_thenReturnZeroNumberOfTraces(){
        assertThat(MeasureNoOfTracesWithHopsLimit.calc(C, C, maxHops -> maxHops <= 0, 0), equalTo(0));
    }
}