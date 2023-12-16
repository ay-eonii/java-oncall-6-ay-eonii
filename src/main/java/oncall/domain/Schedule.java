package oncall.domain;

import java.time.Month;

public class Schedule {
    private final Month month;
    private final DayOfWeek dayOfWeek;

    public Schedule(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
