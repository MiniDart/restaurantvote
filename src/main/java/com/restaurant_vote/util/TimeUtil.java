package com.restaurant_vote.util;

import java.time.LocalDate;


public class TimeUtil {
    public static final LocalDate LOCAL_DATE_MAX=LocalDate.of(2100,01,01);
    public static final LocalDate LOCAL_DATE_MIN=LocalDate.of(1900,01,01);


    public static class Period{
        private final LocalDate start;
        private final LocalDate end;

        public Period(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getEnd() {
            return end;
        }
    }
    public static Period createPeriod(LocalDate start, LocalDate end){
        return new Period(start==null?LOCAL_DATE_MIN:start, end==null?LOCAL_DATE_MAX:end);
    }
}
