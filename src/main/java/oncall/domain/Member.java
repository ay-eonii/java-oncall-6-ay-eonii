package oncall.domain;

import oncall.view.constant.ExceptionMessage;

public class Member {
    private final String name;

    public Member(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() > Range.MAX.value) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    private enum Range {
        MAX(5);

        private final int value;

        Range(int value) {
            this.value = value;
        }
    }
}
