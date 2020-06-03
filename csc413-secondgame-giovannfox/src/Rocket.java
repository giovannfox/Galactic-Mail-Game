package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Rocket extends Objects {

    // score
    private int score;

    // movement and rotation speed
    private double speed;

    //on moon or not
    private boolean onMoon;
    private boolean isShot;


    public Rocket(double locX, double locY, double dir) {

        img = loadImage("Resources/Landed.gif");

        score = 0;

        this.locX = locX;
        this.locY = locY;

        // start speed
        speed = 4;

        centX = locX + (img.getWidth() / 2);
        centY = locY + (img.getHeight() / 2);

        impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
        onMoon = true;
        isShot = false;
        this.dir = dir;
    }


    public void setImage(String attribute) {
        img = loadImage("Resources/Flying.gif");
    }

    public double getDirection() {
        return dir;
    }

    public double getMyX() {
        return locX;
    }

    public double getMyY() {
        return locY;
    }

    public double getCenterX() {
        return centX;
    }

    public double getCenterY() {
        return centY;
    }

    public void launchRocket() {
        locX = locX + speed * Math.cos(Math.toRadians(dir));
        locY = locY + speed * Math.sin(Math.toRadians(dir));
        centX = centX + speed * Math.cos(Math.toRadians(dir));
        centY = centY + speed * Math.sin(Math.toRadians(dir));
        move = AffineTransform.getTranslateInstance(speed * Math.cos(Math.toRadians(dir)), speed * Math.sin(Math.toRadians(dir)));
        impact = move.createTransformedShape(impact);
    }

    public void setIsShot(boolean isShot) {
        this.isShot = isShot;
    }

    public Shape getRec() {
        return impact;
    }

    public boolean getOnMoon() {
        return onMoon;
    }

    public void move(Keyboard k) {
        if (k.getL()) {
            dir = dir - speed;
            if (dir < -360) {
                dir = dir + 360;
            }
            move = AffineTransform.getRotateInstance(Math.toRadians(-speed), centX, centY);
            impact = move.createTransformedShape(impact);
        }
        if (k.getR()) {
            dir = dir + speed;
            if (dir > 360) {
                dir = dir - 360;
            }
            move = AffineTransform.getRotateInstance(Math.toRadians(speed), centX, centY);
            impact = move.createTransformedShape(impact);
        }
        // rocket launch
        if (isShot) {
            launchRocket();
            k.setShoot();
            if (locX > 1200) {
                locX = 0;
                impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
            } else if (locY > 650) {
                locY = 0;
                impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
            } else if (locX < 0) {
                locX = 1199;
                impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
            } else if (locY < 0) {
                locY = 649;
                impact = new Rectangle2D.Double(locX, locY, img.getWidth(), img.getHeight());
            }
            centX = locX + (img.getWidth() / 2);
            centY = locY + (img.getHeight() / 2);
            this.setImage("");
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setOnMoon(boolean onMoon) {
        this.onMoon = onMoon;
    }

    public int getScore() {
        return score;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }



    public void nextLevel(double myX, double myY, double direction) {
        this.speed = 4;
        this.locX = myX;
        this.locY = myY;

        this.centX = myX + (img.getWidth() / 2);
        this.centY = myY + (img.getHeight() / 2);


        impact = new Rectangle2D.Double(myX, myY, img.getWidth(), img.getHeight());
        onMoon = true;
        isShot = false;
        this.dir = direction;
    }
}
