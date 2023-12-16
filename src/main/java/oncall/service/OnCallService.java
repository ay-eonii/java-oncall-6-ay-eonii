package oncall.service;


import oncall.domain.*;
import oncall.domain.repository.OnCallReposiroty;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class OnCallService {
    private static final String DATE_FORMAT = "%s월 %s일 %s";
    private static final String HOLIDAY_FORMAT = "%s(휴일)";

    private final OnCallReposiroty repository = OnCallReposiroty.getInstance();

    public List<String> getDate(Schedule schedule) {
        Month month = schedule.getMonth();
        DayOfWeek dayOfWeek = schedule.getDayOfWeek();
        repository.save(dayOfWeek);

        Calendar cal = Calendar.getInstance();
        cal.set(2023, month.getValue() - 1, 1);
        int calActualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        List<String> dates = new ArrayList<>();
        while (repository.getLastDay() <= calActualMaximum) {
            int lastDay = repository.getLastDay();
            DayOfWeek lastDayOfWeek = repository.getLastDayOfWeek();
            String date = String.format(DATE_FORMAT, month.getValue(), lastDay, lastDayOfWeek.toString());
            if (Holiday.match(date) != Holiday.NONE) {
                date = String.format(HOLIDAY_FORMAT, date);
            }
            dates.add(date);
            repository.updateLastDayOfWeek();
            repository.updateLastDay();
        }
        return dates;
    }

    public List<String> getOnCall(List<String> dates, Map<Type, OnCall> onCallMap) {
        List<String> onCall = new ArrayList<>();
        for (String date : dates) {
            Member member;
            if (date.contains(" 토") || date.contains(" 일") || dates.contains("휴일")) {
                member = onCallMap.get(Type.WEEKEND).getNextOnCall();
                onCall.add(member.toString());
                continue;
            }
            member = onCallMap.get(Type.WEEKDAY).getNextOnCall();
            onCall.add(member.toString());
        }
        return onCall;
    }

}
