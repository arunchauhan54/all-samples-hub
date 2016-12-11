package singh.arun.program;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Puzzle1 {

    public Puzzle1() {
    }

    public static void main(String[] args) {
        final Puzzle1 puzzle1 = new Puzzle1();
        final List<LongPredicate> listOfPredicate = new ArrayList<>();
        listOfPredicate.add(puzzle1.isDivisibleFrom(3));
        listOfPredicate.add(puzzle1.isDivisibleFrom(5));
        System.out.print(puzzle1.sumOfValues(1000, listOfPredicate));
    }

    private long sumOfValues(final long limit, List<LongPredicate> listOfPredicate) {
        return LongStream.range(1, limit)
                .filter(value -> listOfPredicate
                        .stream()
                        .anyMatch(longPredicate -> longPredicate.test(value)))
                .sum();
    }

    private LongPredicate isDivisibleFrom(int n) {
        return (value) -> value % n == 0;
    }
}
