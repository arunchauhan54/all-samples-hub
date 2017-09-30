package singh.arun.program;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Puzzle1 {

    public static void main(String[] args) {
        System.out.print(Puzzle1.sumOfValues(1000, isDivisibleFrom(3).and(isDivisibleFrom(5))));
    }

    public static long sumOfValues(final long limit, LongPredicate p) {
        return LongStream.range(1, limit)
                .filter(p)
                .sum();
    }

    public static LongPredicate isDivisibleFrom(int n) {
        return (value) -> value % n == 0;
    }
}
