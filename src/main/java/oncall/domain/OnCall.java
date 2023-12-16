package oncall.domain;

import java.util.Queue;

public abstract class OnCall {
    private final Queue<Member> order;

    public OnCall(Queue<Member> order) {
        this.order = order;
    }
}
