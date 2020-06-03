package src;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;

public class MailMoon extends Objects {
    private boolean on;
    private final boolean startMoon;

    public MailMoon() {
        img = loadImage("Resources/Planets.gif");

        on = false;
        startMoon = false;
        dir = 0;

        locX = 0;
        locY = 0;

        centX = locX + (img.getWidth() / 2);
        centY = locY + (img.getHeight() / 2);

    }

    // sets random location for moons but evenly distributed horizontally
    public static void setMoons(ArrayList<MailMoon> mm) {
        double xPlacement = 210, yPlacement;
        for (int i = 0; i < mm.size(); i++) {
            yPlacement = 500 * Math.random();
            mm.get(i).setX(xPlacement * (i + 1));
            mm.get(i).setY(yPlacement);
        }
    }

    //first moon constructor
    public MailMoon(double locX, double locY) {
        img = loadImage("Resources/Moon.gif");

        centX = locX + (img.getWidth() / 2);
        centY = locY + (img.getHeight() / 2);

        this.locX = locX;
        this.locY = locY;

        dir = 0;
        impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
        move = AffineTransform.getTranslateInstance(locY, locY);

        on = true;
        startMoon = true;
    }

    // sets y locations of moons
    private void setY(double locY) {
        this.locY = locY;
        impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
        move = AffineTransform.getTranslateInstance(locY, locY);
    }

    // sets x location of moons
    private void setX(double locX) {
        this.locX = locX;
        impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
        move = AffineTransform.getTranslateInstance(locY, locY);
    }

    // collision shape
    public Shape getRec() {
        return impact;
    }
    // returns boolean value for onMoon
    public boolean getOn() {
        return on;
    }
    // checks if starting moon
    public boolean getStartMoon() {
        return startMoon;
    }

    // sets player on moon or not
    public void setOn(boolean on) {
        this.on = on;
    }

}
