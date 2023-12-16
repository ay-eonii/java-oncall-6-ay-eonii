package oncall.view;


import camp.nextstep.edu.missionutils.Console;
import oncall.domain.DayOfWeek;
import oncall.domain.OnCall;
import oncall.validator.DateValidator;

import java.time.Month;

public class InputView {
    private static final String MONTH_AND_DAY = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";

    public OnCall readOnCall() {
        System.out.println(MONTH_AND_DAY);
        return getOnCall();
    }

    private OnCall getOnCall() {
        try {
            String[] input = readWithoutSpace().split(",");
            Month month = DateValidator.validateMonth(input[0]);
            DayOfWeek dayOfWeek = DateValidator.validateDayOfWeek(input[1]);
            return new OnCall(month, dayOfWeek);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOnCall();
        }
    }

    private String readWithoutSpace() {
        return Console.readLine().replaceAll("\\s+", "");
    }
}
