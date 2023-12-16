package oncall.domain;

import java.util.Queue;

public class WeekEndOnCall extends OnCall {
    public WeekEndOnCall(Queue<Member> order) {
        super(order);
    }
}
