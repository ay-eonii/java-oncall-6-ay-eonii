package oncall.service;


import oncall.domain.*;
import oncall.domain.repository.OnCallRepository;

import java.time.Month;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OnCallService {
    private static final String DATE_FORMAT = "%s월 %s일 %s";
    private static final String HOLIDAY_FORMAT = "%s(휴일)";

    private final OnCallRepository repository = OnCallRepository.getInstance();

    public List<String> getDate(Schedule schedule) {
        Month month = schedule.getMonth();
        DayOfWeek dayOfWeek = schedule.getDayOfWeek();
        int maximumDate = schedule.calculateActualMaximum();

        repository.save(dayOfWeek);
        List<String> dates = new ArrayList<>();
        while (repository.getLastDay() <= maximumDate) {
            int lastDay = repository.getLastDay();
            DayOfWeek lastDayOfWeek = repository.getLastDayOfWeek();
            String date = String.format(DATE_FORMAT, month.getValue(), lastDay, lastDayOfWeek.toString());
            if (Holiday.match(date) != Holiday.NONE) {
                date = String.format(HOLIDAY_FORMAT, date);
            }
            dates.add(date);
            repository.updateDate();
        }
        return dates;
    }

    public List<String> getOnCall(List<String> dates, Map<Type, OnCall> onCallMap) {
        List<String> onCall = new LinkedList<>();

        for (String date : dates) {
            Type type = Type.of(date);

            Member member;
            if (repository.hasSkipMember(type)) {
                member = repository.getSkipMember(type);
                onCall.add(member.toString());
                repository.resetSkipMember(type);
                continue;
            }

            member = onCallMap.get(type).getNextOnCall();

            if (checkSequence(type, member)) {
                member = onCallMap.get(type).getNextOnCall();
            }

            onCall.add(member.toString());
            repository.updateLastMember(member);
        }
        return onCall;
    }

    private boolean checkSequence(Type type, Member member) {
        Member lastMember = repository.getLastMember();
        if (member.equals(lastMember)) {
            repository.saveSkipMember(type, member);
            return true;
        }
        return false;
    }

    public void reset() {
        repository.reset();
    }
}
