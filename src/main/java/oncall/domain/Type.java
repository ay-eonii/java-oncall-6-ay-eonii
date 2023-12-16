package oncall.domain;

public enum Type {
    WEEKDAY, WEEKEND;

    public static Type of(String date) {
        if (date.contains(" 토") || date.contains(" 일") || date.contains("휴일")) {
            return Type.WEEKEND;
        }
        return Type.WEEKDAY;
    }
}
