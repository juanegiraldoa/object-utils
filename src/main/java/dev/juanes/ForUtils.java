package dev.juanes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForUtils {
    public static Iterable<Integer> range(int start, int end, int step) {
        if (step == 0) throw new IllegalArgumentException("Step cannot be zero");
        return () -> new Iterator<>() {
            private int current = start;

            @Override
            public boolean hasNext() {
                if (step > 0) return current <= end;
                else return current >= end;
            }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                int result = current;
                current += step;
                return result;
            }
        };
    }

    public static Iterable<Integer> range(int start, int end) {
        return range(start, end, 1);
    }

    public static Iterable<LocalDate> range(LocalDate start, LocalDate end, Integer amount, ChronoUnit unit) {
        return () -> new Iterator<>() {
            private LocalDate current = start;

            @Override
            public boolean hasNext() {
                return !current.isAfter(end);
            }

            @Override
            public LocalDate next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                LocalDate result = current;
                current = current.plus(amount, unit);
                return result;
            }
        };
    }

    public static Iterable<LocalDate> range(LocalDate start, LocalDate end, ChronoUnit unit) {
        return range(start, end, 1, unit);
    }
}
