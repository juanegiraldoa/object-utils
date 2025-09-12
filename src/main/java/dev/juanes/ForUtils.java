package dev.juanes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForUtils {
    public static Iterable<Integer> range(int start, int end) {
        return () -> new Iterator<>() {
            private int current = start;

            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Integer next() {
                return current++;
            }
        };
    }

    public static Iterable<LocalDate> range(LocalDate start, LocalDate end, Integer amount, ChronoUnit unit) {
        return () -> new Iterator<>() {
            private LocalDate current = start;

            @Override
            public boolean hasNext() {
                return current.isBefore(end.plus(amount, unit));
            }

            @Override
            public LocalDate next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                LocalDate result = current;
                current = current.plus(1, unit);
                return result;
            }
        };
    }

    public static Iterable<LocalDate> range(LocalDate start, LocalDate end, ChronoUnit unit) {
        return range(start, end, 1, unit);
    }
}
