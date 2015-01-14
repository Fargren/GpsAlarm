package epsz.alarmapp;

public abstract class Trigger {
    protected abstract boolean matches(Trigger trigger);
}
