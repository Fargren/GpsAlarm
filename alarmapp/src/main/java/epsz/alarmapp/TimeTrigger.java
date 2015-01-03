package epsz.alarmapp;

public class TimeTrigger extends Trigger {
    private int hour;
    private int minutes;

    public TimeTrigger(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public boolean matches(Trigger trigger) {
        if (trigger instanceof TimeTrigger) {
            TimeTrigger timeTrigger = (TimeTrigger) trigger;
            return this.hour == timeTrigger.hour && this.minutes == timeTrigger.minutes;
        }
        return false;
    }
}
