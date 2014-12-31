package epsz.alarmapp;

public class TimeTrigger {
    private int hour;
    private int minutes;

    public TimeTrigger(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public boolean matches(TimeTrigger trigger) {
        return this.hour == trigger.hour && this.minutes == trigger.minutes;
    }
}
