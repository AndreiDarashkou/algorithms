package com.study.algorithm.util;


import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.valueOf;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Stopwatch {

    private long startTime;
    private long endTime;
    private String taskName;
    private List<Long> timestamps = new ArrayList<>();

    public Stopwatch(String taskName) {
        this.taskName = taskName;
    }

    public Stopwatch start() {
        this.startTime = System.nanoTime();
        return this;
    }

    public Stopwatch stop() {
        this.endTime = System.nanoTime();
        return this;
    }

    public long getElapsed(TimeUnit timeUnit) {
        return timeUnit.convert(endTime - startTime, NANOSECONDS);
    }

    public BigDecimal getElapsedPrecision(TimeUnit timeUnit) {
        return getPrecisionDifference(this.startTime, this.endTime, timeUnit);
    }

    private BigDecimal getPrecisionDifference(long startTime, long endTime, TimeUnit timeUnit) {
        return valueOf(endTime - startTime).divide(valueOf(NANOSECONDS.convert(1, timeUnit))).setScale(2,
                RoundingMode.CEILING);
    }

    public String prettyPrintString(TimeUnit timeUnit) {
        return String.format("Task: %s took ", taskName)
                + getElapsedPrecision(timeUnit).toPlainString()
                + (timestamps.isEmpty() ? " " : " " + getElapsedIntervalString(timeUnit) + " ")
                + timeUnit;
    }

    private String getElapsedIntervalString(TimeUnit timeUnit) {
        List<String> intervals = new ArrayList<>();
        ListIterator<Long> iterator = timestamps.listIterator();
        long lastInterval = startTime;
        while (iterator.hasNext()) {
            Long currentInterval = iterator.next();
            intervals.add(getPrecisionDifference(lastInterval, currentInterval, timeUnit).toPlainString());
            lastInterval = currentInterval;
        }
        intervals.add(getPrecisionDifference(lastInterval, this.endTime, timeUnit).toPlainString());
        return Arrays.toString(intervals.toArray());
    }

    public Appendable prettyPrint(Appendable writeTo, TimeUnit timeUnit) throws IOException {
        return writeTo.append(prettyPrintString(timeUnit));
    }

    public void prettyPrint(PrintStream ps, TimeUnit timeUnit) {
        ps.println(prettyPrintString(timeUnit));
    }
}
