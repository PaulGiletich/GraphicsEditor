package com.pgiletich.graphics.debugger;

public class Debugger {
    private static int steps = 0;
    private int stepsLeft;
    private static boolean enabled = false;

    private Debugger(int steps){
        this.stepsLeft = steps;
    }

    public static Debugger getDebugger() {
        return new Debugger(steps);
    }

    public boolean hasNextStep(){
        if(isEnabled()){
            return stepsLeft-- > 0;
        }
        else{
            return true;
        }
    }

    public static void addStep() {
        steps++;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        Debugger.enabled = enabled;
        steps = 0;
    }
}
