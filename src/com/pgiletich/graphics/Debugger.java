package com.pgiletich.graphics;

public class Debugger {
    private static int staticSteps = 0;
    private int steps;
    private static boolean enabled = false;

    private Debugger(int steps){
        this.steps = steps;
    }

    public static Debugger getDebugger() {
        if(Debugger.enabled){
            return new Debugger(staticSteps);
        }
        else{
            return new Debugger(Integer.MAX_VALUE);
        }
    }

    public boolean nextStep(){
        return steps-- <= 0;
    }

    public static void addStep() {
        staticSteps++;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        Debugger.enabled = enabled;
        staticSteps = 0;
    }
}
