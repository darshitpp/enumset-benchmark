package dev.darshit.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(2)
public class FilesNIOBenchmark {


    private static final String FILE_NAME = "FilesNIOBenchmarkDummyData.json";
    private static final String FILE_PATH = "src/test/resources/" + FILE_NAME;

    @Benchmark
    public byte[] testLegacyFileOutputStream() {
        File fileToRead = new File(FILE_PATH);
        byte[] bytes = new byte[(int) fileToRead.length()];
        int read;
        try (FileInputStream fileInputStream = new FileInputStream(fileToRead)) {
            read = fileInputStream.read(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return read > 0 ? bytes : null;
    }

    @Benchmark
    public byte[] testNioFilesNewInputStream() {
        Path filePathToRead = Paths.get(FILE_PATH);
        try {
            return Files.readAllBytes(filePathToRead);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options gc = new OptionsBuilder().include(".*" + FilesNIOBenchmark.class.getSimpleName() + ".*")
                .forks(2)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(5L))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(5L))
                .timeout(TimeValue.seconds(20))
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .addProfiler(GCProfiler.class)
                .jvmArgs("-XX:+UseG1GC", "-Xmx500m")
                .build();

        Options thrpt = new OptionsBuilder().include(".*" + FilesNIOBenchmark.class.getSimpleName() + ".*")
                .forks(2)
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(5L))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(5L))
                .timeout(TimeValue.seconds(20))
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .jvmArgs("-XX:+UseG1GC", "-Xmx500m")
                .build();

        new Runner(gc).run();
        new Runner(thrpt).run();
    }
}
