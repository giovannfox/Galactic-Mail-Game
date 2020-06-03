package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Shape;


public abstract class Objects {

    // x y location
    protected double locX;
    protected double locY;

    //objects direction
    protected double dir;
    protected AffineTransform move;
    protected AffineTransform original = new AffineTransform();
    protected Shape impact;

    //center x and y location
    protected double centX;
    protected double centY;

    //from load image
    protected BufferedImage img;

    protected void draw(Graphics2D G) {
        G.setTransform(original);
        G.rotate(Math.toRadians(dir), centX, centY);
        G.drawImage(img, (int) locX, (int) locY, null);
        G.setTransform(original);
    }

    //to create buffered image for objects
    protected final BufferedImage loadImage(String pathName) {
        try {
            return ImageIO.read(new File(pathName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
