package oncall.domain;

import java.util.Arrays;

public enum Holiday {
    NEW_YEAR("신정", "1월 1일"),
    INDEPENDENCE_MOVEMENT_DAY("삼일절", "3월 1일"),
    CHILDRENS_DAY("어린이날", "5월 5일"),
    MEMORIAL_DAY("현충일", "6월 6일"),
    LIBERATION_DAY("광복절", "8월 15일"),
    NATIONAL_FOUNDATION_DAY("개천절", "10월 3일"),
    HANGEUL_DAY("한글날", "10월 9일"),
    CHRISTMAS("성탄절", "12월 25일"),
    NONE("평일", "0월 0일");

    private final String name;
    private final String date;

    Holiday(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public static Holiday match(String input) {
        return Arrays.stream(values())
                .filter(value -> value.date.equals(input))
                .findFirst()
                .orElse(NONE);
    }
}

