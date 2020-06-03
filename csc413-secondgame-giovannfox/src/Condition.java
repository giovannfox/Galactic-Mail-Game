package src;

import java.awt.*;

public abstract class Condition {
    // manage object
    private static Condition currCondition = null;
    protected Manage man;
    // manage object initialization
    public Condition(Manage man) {
        this.man = man;
    }
    public static Condition getState() {
        return currCondition;
    }
    public static void setState(Condition cond) {
        currCondition = cond;
    }
    // objects draw
    public abstract void draw(Graphics G);
    // updates states
    public abstract void tick();
}
