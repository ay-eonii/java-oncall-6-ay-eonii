package oncall.view;


import camp.nextstep.edu.missionutils.Console;
import oncall.domain.DayOfWeek;
import oncall.domain.Schedule;
import oncall.domain.WeekdayOnCall;
import oncall.validator.DateValidator;

import java.time.Month;

public class InputView {
    private static final String SCHEDULE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_ONCALL = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public Schedule readSchedule() {
        System.out.print(SCHEDULE);
        return getSchedule();
    }

    private Schedule getSchedule() {
        try {
            String[] input = readWithoutSpace().split(",");
            Month month = DateValidator.validateMonth(input[0]);
            DayOfWeek dayOfWeek = DateValidator.validateDayOfWeek(input[1]);
            return new Schedule(month, dayOfWeek);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getSchedule();
        }
    }

    private String readWithoutSpace() {
        return Console.readLine().replaceAll("\\s+", "");
    }
}
