# Java Benchmarks

## EnumSet Benchmark
Surprisingly (or not?), HashSet is slower than Array based implementations, though EnumSet trumps all!

```bash
Benchmark                           Mode  Cnt          Score          Error  Units
EnumSetBenchmark.testArray         thrpt   10  207516745.597 ±  3323680.044  ops/s
EnumSetBenchmark.testEnumSet       thrpt   10  328086167.213 ±  2810648.550  ops/s
EnumSetBenchmark.testHashSet       thrpt   10  190053025.512 ± 20865732.003  ops/s
EnumSetBenchmark.testUtilsIsOneOf  thrpt   10  183638753.933 ±  3412488.068  ops/s
```

## FilesNIOBenchmark

### GC Benchmark
```bash
Benchmark                                                                       Mode  Cnt       Score      Error   Units
FilesNIOBenchmark.testLegacyFileOutputStream                                   thrpt   10  106080.171 ± 1261.091   ops/s
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.alloc.rate                    thrpt   10     252.811 ±    2.997  MB/sec
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.alloc.rate.norm               thrpt   10    2752.001 ±    0.001    B/op
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Eden_Space           thrpt   10     251.633 ±    9.043  MB/sec
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Eden_Space.norm      thrpt   10    2739.098 ±   87.557    B/op
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Old_Gen              thrpt   10       0.521 ±    0.053  MB/sec
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Old_Gen.norm         thrpt   10       5.674 ±    0.574    B/op
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Survivor_Space       thrpt   10       3.648 ±    0.301  MB/sec
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.churn.G1_Survivor_Space.norm  thrpt   10      39.712 ±    3.327    B/op
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.count                         thrpt   10     200.000             counts
FilesNIOBenchmark.testLegacyFileOutputStream:·gc.time                          thrpt   10    2208.000                 ms
FilesNIOBenchmark.testNioFilesNewInputStream                                   thrpt   10  130849.569 ± 1424.651   ops/s
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.alloc.rate                    thrpt   10     372.095 ±    4.619  MB/sec
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.alloc.rate.norm               thrpt   10    3284.001 ±    6.375    B/op
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.churn.G1_Eden_Space           thrpt   10     372.889 ±   10.604  MB/sec
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.churn.G1_Eden_Space.norm      thrpt   10    3291.019 ±   85.516    B/op
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.churn.G1_Old_Gen              thrpt   10       0.005 ±    0.001  MB/sec
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.churn.G1_Old_Gen.norm         thrpt   10       0.040 ±    0.012    B/op
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.count                         thrpt   10     274.000             counts
FilesNIOBenchmark.testNioFilesNewInputStream:·gc.time                          thrpt   10     180.000                 ms
```

### Throughput Benchmark
```bash
Benchmark                                      Mode  Cnt       Score      Error  Units
FilesNIOBenchmark.testLegacyFileOutputStream  thrpt   10  105213.549 ± 2104.512  ops/s
FilesNIOBenchmark.testNioFilesNewInputStream  thrpt   10  132439.203 ±  971.357  ops/s
```