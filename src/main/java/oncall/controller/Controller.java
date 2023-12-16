package oncall.controller;


import oncall.domain.*;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final OnCallService onCallService = new OnCallService();

    public void execute() {
        Schedule schedule = inputView.readSchedule();
        Map<Type, OnCall> typeOnCallMap = readOnCallOrder();
        List<String> date = onCallService.getDate(schedule);
        List<String> onCall = onCallService.getOnCall(date, typeOnCallMap);
        outputView.printOnCall(date, onCall);
        onCallService.reset();
    }

    private Map<Type, OnCall> readOnCallOrder() {
        try {
            WeekdayOnCall weekdayOnCall = inputView.readWeekdayOrder();
            WeekEndOnCall weekEndOnCall = inputView.readWeekendOrder();
            Map<Type, OnCall> onCall = new HashMap<>();
            onCall.put(Type.WEEKDAY, weekdayOnCall);
            onCall.put(Type.WEEKEND, weekEndOnCall);
            return onCall;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readOnCallOrder();
        }
    }
}
