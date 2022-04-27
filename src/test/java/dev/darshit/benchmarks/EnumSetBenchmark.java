package dev.darshit.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
public class EnumSetBenchmark {

    public enum Week {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }
    private Week[] DAYS;
    private Week DAY_TO_TEST;
    private Set<Week> HASHSET_WEEKDAYS;
    private EnumSet<Week> ENUMSET_WEEKDAYS;

    @Setup
    public void setUp() {
        DAY_TO_TEST = Week.SATURDAY;
        DAYS = Week.values();
        HASHSET_WEEKDAYS = new HashSet<>(Arrays.asList(DAYS));
        ENUMSET_WEEKDAYS = EnumSet.allOf(Week.class);
    }

    @Benchmark
    public boolean testUtilsIsOneOf() {
        return isOneOf(DAY_TO_TEST, Week.SUNDAY, Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY, Week.FRIDAY, Week.SATURDAY);
    }

    @Benchmark
    public boolean testHashSet() {
        return HASHSET_WEEKDAYS.contains(DAY_TO_TEST);
    }

    @Benchmark
    public boolean testEnumSet() {
        return ENUMSET_WEEKDAYS.contains(DAY_TO_TEST);
    }

    @Benchmark
    public boolean testArray() {
        for (Week day : DAYS) {
            if(DAY_TO_TEST == day) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean isOneOf(T item, T ... items) {
        if (item != null && items != null) {
            for (T current : items) {
                if (item.equals(current)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws RunnerException
    {
         Options options = new OptionsBuilder().include(".*" + EnumSetBenchmark.class.getSimpleName() + ".*")
                .forks(2)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(5L))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(5L))
                .timeout(TimeValue.seconds(20))
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .build();
        new Runner(options).run();
    }
}
