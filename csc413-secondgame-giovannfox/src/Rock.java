package src;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

public class Rock extends Objects {

    //rocks rotation and movement speed
    private double rotSpeed;

    private double speed;

    public void setRotSpeed(int rotSpeed) {
        this.rotSpeed = rotSpeed;
    }

    public double getCenterX() {
        return centX;
    }

    public double getCenterY() {
        return centY;
    }

    public double getDirection() {
        return dir;
    }

    public Shape getRec() {
        return impact;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getMyX() {
        return locX;
    }

    public double getMyY() {
        return locY;
    }



    public void move(Keyboard x) {
        // moving the rock
        if (locX > 1300) {
            locX = -img.getWidth() + 1;
            centX = locX + (img.getWidth() / 2);
            impact = new Rectangle2D.Double(locX + (img.getWidth() * .3),
                    locY + (img.getHeight() * .3), (img.getWidth() * .5),
                    (img.getHeight()) * .5);
            move = AffineTransform.getTranslateInstance(locY, locY);
        } else if (locY > 750) {
            locY = -img.getHeight() + 1;
            centY = locY + (img.getHeight() / 2);
            impact = new Rectangle2D.Double(locX + (img.getWidth() * .3),
                    locY + (img.getHeight() * .3), (img.getWidth() * .5),
                    (img.getHeight()) * .5);
            move = AffineTransform.getTranslateInstance(locY, locY);
        } else if (locX < -img.getWidth()) {
            locX = 1199;
            centX = locX + (img.getWidth() / 2);
            impact = new Rectangle2D.Double(locX + (img.getWidth() * .3),
                    locY + (img.getHeight() * .2), (img.getWidth() * .5),
                    (img.getHeight()) * .5);
            move = AffineTransform.getTranslateInstance(locY, locY);
        } else if (locY < -img.getHeight()) {
            locY = 649;
            centY = locY + (img.getHeight() / 2);
            impact = new Rectangle2D.Double(locX + (img.getWidth() * .3),
                    locY + (img.getHeight() * .3), (img.getWidth() * .5),
                    (img.getHeight()) * .5);
            move = AffineTransform.getTranslateInstance(locY, locY);
        }
        locX = locX + speed;
        locY = locY + speed;
        centX = centX + speed;
        centY = centY + speed;
        dir = dir + rotSpeed;
        move = AffineTransform.getTranslateInstance(speed, speed);
        impact = move.createTransformedShape(impact);
    }

    public Rock(double locX, double locY) {
        img = loadImage("resources/Asteroid.gif");
        this.locX = locX;
        this.locY = locY;

        //random movement speed
        if (Math.random() < .6) {
            speed = 3 * Math.random() + .6;
        } else {
            speed = -3 * Math.random() - .6;
        }

        centX = locX + (img.getWidth() / 2);
        centY = locY + (img.getHeight() / 2);
        dir = Math.random();

        //random rotation speed
        if (Math.random() < .6) {
            rotSpeed = 6 * Math.random();
        } else {
            rotSpeed = (-6) * Math.random();
        }
        impact = new Rectangle2D.Double(locX + (img.getWidth() * .3),
                locY + (img.getHeight() * .3), (img.getWidth() * .5),
                (img.getHeight()) * .5);
        move = AffineTransform.getTranslateInstance(locY, locY);
    }

}
