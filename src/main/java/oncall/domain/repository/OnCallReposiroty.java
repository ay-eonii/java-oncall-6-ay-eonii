package oncall.domain.repository;

import oncall.domain.DayOfWeek;

public class OnCallReposiroty {
    private static OnCallReposiroty instance;
    private DayOfWeek lastDayOfWeek;
    private int lastDay = 1;

    private OnCallReposiroty() {
    }

    public static OnCallReposiroty getInstance() {
        if (instance == null) {
            instance = new OnCallReposiroty();
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

    public void updateLastDay() {
        lastDay++;
    }

    public void updateLastDayOfWeek() {
        lastDayOfWeek = lastDayOfWeek.next();
    }

    public void reset() {
        lastDayOfWeek = null;
        lastDay = 1;
    }
}
