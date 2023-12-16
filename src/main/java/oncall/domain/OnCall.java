package oncall.domain;

import oncall.view.constant.ExceptionMessage;

import java.util.Queue;

public abstract class OnCall {
    private final Queue<Member> order;

    public OnCall(Queue<Member> order) {
        validate(order);
        this.order = order;
    }

    private void validate(Queue<Member> order) {
        if (order.size() < Range.MIN.value || order.size() > Range.MAX.value) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MEMBER_COUNT);
        }
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

    private enum Range {
        MIN(5), MAX(35);

        private final int value;

        Range(int value) {
            this.value = value;
        }
    }
}
