package com.szberko.ditributedtracing;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.szberko.ditributedtracing.Provider.inputData;
import static com.szberko.ditributedtracing.Provider.graph;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class FileUtilityTest {


    @Test
    void test(){
        Graph something =  FileUtility.parseGraph(Stream.of(inputData));

        assertThat(something, equalTo(graph));
    }
}