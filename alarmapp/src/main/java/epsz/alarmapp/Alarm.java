package epsz.alarmapp;

public class Alarm {
    Trigger passiveTrigger;

    public void addTrigger(Trigger trigger) {
        this.passiveTrigger = trigger;
    }

    public boolean shouldRing(Trigger activeTrigger) {
        return activeTrigger.matches(passiveTrigger);
    }
}
