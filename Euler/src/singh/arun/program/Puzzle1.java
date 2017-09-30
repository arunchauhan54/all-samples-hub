package singh.arun.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Puzzle1 {

    public static void main(String[] args) {
        final Collection<LongPredicate> listOfPredicate = new ArrayList<>();
        listOfPredicate.add(isDivisibleFrom(3));
        listOfPredicate.add((isDivisibleFrom(5)));
        System.out.print(Puzzle1.sumOfValues(1000, listOfPredicate));
    }

    public static long sumOfValues(final long limit, Collection<LongPredicate> listOfPredicate) {
        return LongStream.range(1, limit)
                .filter(listOfPredicate.stream().reduce(LongPredicate::or).orElse(o -> false))
                .sum();
    }

    private static LongPredicate isDivisibleFrom(int n) {
        return (value) -> value % n == 0;
    }
}
