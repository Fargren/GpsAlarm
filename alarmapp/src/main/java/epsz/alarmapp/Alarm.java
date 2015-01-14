package epsz.alarmapp;

public class Alarm {
    Trigger passiveTrigger;
    public boolean isRinging;

    public void addTrigger(Trigger trigger) {
        this.passiveTrigger = trigger;
    }

    private boolean shouldRing(Trigger activeTrigger) {
        return activeTrigger.matches(passiveTrigger);
    }


    public void updateTo(Trigger trigger) {
        if(shouldRing(trigger)) {
            isRinging = true;
        }
    }
}
