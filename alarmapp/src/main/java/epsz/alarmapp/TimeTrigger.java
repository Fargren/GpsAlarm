package epsz.alarmapp;

import epsz.alarmapp.requests.HourTime;

public class TimeTrigger extends Trigger {
    private HourTime time;

    public TimeTrigger(HourTime time) {
        this.time = time;
    }

    @Override
    public boolean matches(Trigger trigger) {
        if (trigger instanceof TimeTrigger) {
            TimeTrigger timeTrigger = (TimeTrigger) trigger;
            return time.hour == timeTrigger.time.hour && time.minutes == timeTrigger.time.minutes;
        }
        return false;
    }
}
