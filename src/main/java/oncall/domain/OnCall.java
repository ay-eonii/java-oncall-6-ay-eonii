package oncall.domain;

import java.util.Queue;

public abstract class OnCall {
    private final Queue<Member> order;

    public OnCall(Queue<Member> order) {
        this.order = order;
    }

    public Member getNextOnCall() {
        Member member = order.poll();
        order.offer(member);
        return member;
    }

    @Override
    public String toString() {
        if (order.isEmpty()) {
            return "";
        }
        return order.peek().toString();
    }
}
