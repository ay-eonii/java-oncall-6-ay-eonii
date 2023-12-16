package oncall.domain.repository;

import oncall.domain.DayOfWeek;
import oncall.domain.Member;
import oncall.domain.Type;

import java.util.HashMap;
import java.util.Map;

public class OnCallRepository {
    private static OnCallRepository instance;
    private DayOfWeek lastDayOfWeek;
    private Member lastMember;
    private Map<Type, Member> skipMember;
    private int lastDay = 1;

    private OnCallRepository() {
        Map<Type, Member> skipMember = new HashMap<>();
        skipMember.put(Type.WEEKDAY, null);
        skipMember.put(Type.WEEKEND, null);
        this.skipMember = skipMember;
    }

    public static OnCallRepository getInstance() {
        if (instance == null) {
            instance = new OnCallRepository();
        }
        return instance;
    }

    public void save(DayOfWeek dayOfWeek) {
        lastDayOfWeek = dayOfWeek;
    }

    public int getLastDay() {
        return lastDay;
    }

    public DayOfWeek getLastDayOfWeek() {
        return lastDayOfWeek;
    }

    public void updateDate() {
        lastDay++;
        lastDayOfWeek = lastDayOfWeek.next();
    }

    public void reset() {
        lastDayOfWeek = null;
        lastDay = 1;
    }

    public void updateLastMember(Member member) {
        lastMember = member;
    }

    public Member getLastMember() {
        return lastMember;
    }

    public void saveSkipMember(Type type, Member member) {
        skipMember.put(type, member);
    }

    public void resetSkipMember(Type type) {
        skipMember.put(type, null);
    }

    public boolean hasSkipMember(Type type) {
        return skipMember.get(type) != null;
    }

    public Member getSkipMember(Type type) {
        return skipMember.get(type);
    }
}
