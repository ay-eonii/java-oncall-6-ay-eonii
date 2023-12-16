package oncall.domain;


import oncall.view.constant.ExceptionMessage;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    private final String korean;
    private final int value;

    DayOfWeek(String korean, int value) {
        this.korean = korean;
        this.value = value;
    }

    public static DayOfWeek match(String input) {
        return Arrays.stream(values())
                .filter(value -> value.korean.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.INVALID_SCHEDULE));
    }
}