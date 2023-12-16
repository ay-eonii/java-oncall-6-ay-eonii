package oncall.domain;

import java.util.Queue;

public class WeekdayOnCall extends OnCall {
    public WeekdayOnCall(Queue<Member> order) {
        super(order);
    }
}
