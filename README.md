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