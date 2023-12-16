package oncall.view;


import camp.nextstep.edu.missionutils.Console;
import oncall.domain.DayOfWeek;
import oncall.domain.Member;
import oncall.domain.Schedule;
import oncall.domain.WeekdayOnCall;
import oncall.validator.DateValidator;

import java.time.Month;
import java.util.LinkedList;
import java.util.Queue;

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

    public WeekdayOnCall readWeekdayOrder() {
        System.out.print(WEEKDAY_ONCALL);
        return getWeekdayOrder();
    }

    private WeekdayOnCall getWeekdayOrder() {
        try {
            String[] input = readWithoutSpace().split(",");
            Queue<Member> order = new LinkedList<>();
            for (String name : input) {
                if (name.isEmpty()) {
                    continue;
                }
                order.add(new Member(name));
            }
            return new WeekdayOnCall(order);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWeekdayOrder();
        }
    }


    private String readWithoutSpace() {
        return Console.readLine().replaceAll("\\s+", "");
    }
}
