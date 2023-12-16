package oncall.validator;


import oncall.domain.DayOfWeek;
import oncall.view.constant.ExceptionMessage;

import java.time.DateTimeException;
import java.time.Month;

public class DateValidator {
    public static Month validateMonth(String input) {
        return convertToMonth(input);
    }

    private static Month convertToMonth(String input) {
        try {
            int month = Integer.parseInt(input);
            return Month.of(month);
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID);
        }
    }

    public static DayOfWeek validateDayOfWeek(String input) {
        return DayOfWeek.match(input);
    }
}
