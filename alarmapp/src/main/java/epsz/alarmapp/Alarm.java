package epsz.alarmapp;

public class Alarm {
    TimeTrigger trigger;

    public void addTrigger(TimeTrigger trigger) {
        this.trigger = trigger;
    }

    public boolean shouldRing(TimeTrigger time) {
        return time.matches(trigger);
    }
}
