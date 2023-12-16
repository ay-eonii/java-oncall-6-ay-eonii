package oncall.domain;

import java.time.Month;

public class OnCall {
    private final Month month;
    private final DayOfWeek dayOfWeek;

    public OnCall(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }
}
