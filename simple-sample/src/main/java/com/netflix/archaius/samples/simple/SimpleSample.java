package com.netflix.archaius.samples.simple;

public class SimpleSample {
    public static void main(String args[]) {
        SomeService ss = new SomeService();
        ss.getDirectly();
        ss.getAgainstProperty();
        ss.changePropertyThatIsMonitored();
    }
}
