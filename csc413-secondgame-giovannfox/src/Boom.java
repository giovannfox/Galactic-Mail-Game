package src;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;


public class Boom {
    // array list of images
    private final ArrayList<BufferedImage> imgs;
    // animation
    private int count;
    // image dimensions
    private final int Xlength;
    private final int Ylength;
    // locations
    private final double locX;
    private final double locY;

    public Boom(double locX, double locY, int Xlength, int Ylength, String image) {
        count = 0;
        this.Xlength = Xlength;
        this.Ylength = Ylength;
        this.locX = locX;
        this.locY = locY;
        imgs = new ArrayList(10);
        // load images into array list
        for (int i = 0; i < 10; i++) {
            imgs.add(loadImage("resources/Explosion.gif"));
        }
    }
    // buffered image object
    private BufferedImage loadImage(String pathName) {
        try {
            return ImageIO.read(new File(pathName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    // graphics for explosion
    protected void draw(Graphics2D G) {
        G.drawImage(imgs.get(count), (int) (locX - imgs.get(count).getWidth() / 2), (int) (locY - imgs.get(count).getHeight() / 2), Xlength, Ylength, null);
    }

    // access counter
    public int getCount() {
        return count;
    }

    // updating counter
    public void setCount(int count) {
        this.count = count;
    }

}
