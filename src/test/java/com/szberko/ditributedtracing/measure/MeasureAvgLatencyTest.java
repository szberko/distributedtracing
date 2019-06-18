package com.szberko.ditributedtracing.measure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.szberko.ditributedtracing.providers.Provider.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeasureAvgLatencyTest {

    @Test
    void givenNodes_whenCalculateAvgLatency_thenReturnAvgLatencyBetweenThem(){
        assertThat(MeasureAvgLatency.calc(List.of(A, D)), equalTo("5"));
    }

    @Test
    void givenOneNodes_whenCalculateAvgLatency_thenThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> MeasureAvgLatency.calc(List.of(A)));
    }

    @Test
    void givenNotConnectedNodes_whenCalculateAvgLatency_thenThrowNoSuchTraceException(){
        assertThat(MeasureAvgLatency.calc(List.of(A, E, D)), equalTo("NO SUCH TRACE"));
    }
}