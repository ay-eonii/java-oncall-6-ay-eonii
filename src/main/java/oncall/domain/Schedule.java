package oncall.domain;

import java.time.Month;
import java.util.Calendar;

public class Schedule {
    private final Month month;
    private final DayOfWeek dayOfWeek;

    public Schedule(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public int calculateActualMaximum() {
        Calendar cal = Calendar.getInstance();
        cal.set(2023, month.getValue() - 1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
