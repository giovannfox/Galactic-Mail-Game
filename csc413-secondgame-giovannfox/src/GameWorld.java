package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.image.BufferStrategy;


public class GameWorld implements Runnable {

    // graphics
    private Graphics2D G;

    // impact detector
    private Impact cd;
    private int level;

    // game setup objects
    private JFrame fr;
    private Thread thr;
    private Canvas bg;
    private BufferStrategy bs;

    //image
    private BufferedImage arbg;
    private BufferedImage mb;
    private BufferedImage title;

    //inputs
    private Keyboard km;
    private Control mm;

    //sound
    private Sound mc;
    private Sound bc;
    private Sound launch;

    // states
    private Condition gCond;
    private Condition mCond;
    private Condition hCond;
    //manage
    private Manage man;

    //gameobjects
    private Rocket player;
    private ArrayList<Rock> rocks;
    private ArrayList<MailMoon> mailMoons;
    private ArrayList<MailMoon> startingMailMoon;
    private ArrayList<Boom> booms;

    // booleans
    private boolean isRun;
    private boolean gameDone;
    private boolean newL;

    // begin method
    // initialize game objects
    private void initialize() {
        level = 0;
        //rsources
        title = loadImage("Resources/Title.gif");
        arbg = loadImage("Resources/background.bmp");
        mb = loadImage("Resources/background.bmp");
        mc = new Sound("Resources/Music.mid");
        bc = new Sound("Resources/Music.mid");

        man = new Manage(this);
        gCond = new PlayCondition(man);
        // start init
        mCond = new StartScreen(man);
        hCond = new Info(man);
        newL = true;
        gameDone = false;
        cd = new Impact();
        player = new Rocket(100, 300, 0);
        startingMailMoon = new ArrayList();
        startingMailMoon.add(new MailMoon(80, 280));

        // moons arraylist
        mailMoons = new ArrayList();
        for (int i = 0; i < 4; i++) {
            mailMoons.add(new MailMoon());
        }

        //rocks arraylist
        rocks = new ArrayList();
        for (int i = 0; i < 8; i++) {
            rocks.add(new Rock(1100 * Math.random() + 90, 6000 * Math.random() + 40));
        }

        //explosions arraylist
        booms = new ArrayList();
        MailMoon.setMoons(mailMoons);
        Condition.setState(mCond);
    }

    // new level
    private void nextLevel() {
        arbg = loadImage("Resources/Background.bmp");
        player.nextLevel(100, 300, 0);
        startingMailMoon = new ArrayList();
        startingMailMoon.add(new MailMoon(80, 280));
        rocks = new ArrayList();
        for (int i = 0; i < 8 + 1 * level; i++) {
            rocks.add(new Rock(1100 * Math.random(), 750 * Math.random()));
        }
        mailMoons = new ArrayList();
        for (int i = 0; i < 4; i++) {
            mailMoons.add(new MailMoon());
        }
        booms = new ArrayList();
        MailMoon.setMoons(mailMoons);
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

    //updates location and collisions
    private void update() {
        //make sure games not over to update objects
        if (!gameDone) {
            km.tick();
            if (mailMoons.size() == 0) {
                level++;
                player.setScore(player.getScore() + 100);
                nextLevel();
                newL = true;
            }
            cd.playerMoon(player, startingMailMoon, km);
            cd.playerMoon(player, mailMoons, km);
            if (cd.playerAsteroid(player, rocks, km, booms)) {
                gameDone = true;
            }
            player.move(km);
            if (km.getShoot() && !gameDone) {
                launch = new Sound("resources/Launch.wav");
                launch.playonce();
                newL = false;
                player.setIsShot(true);
                player.move(km);
            }
        }
        for (int i = 0; i < rocks.size(); i++) {
            rocks.get(i).move(km);
        }
    }
    // draw objects and images
    private void draw() {
        bs = bg.getBufferStrategy();
        if (bs == null) {
            bg.createBufferStrategy(2);
            return;
        }
        G = (Graphics2D) bs.getDrawGraphics();
        G.drawImage(arbg, 0, 0, 1250, 700, null);
        if (Condition.getState() == gCond) {
            for (int i = 0; i < rocks.size(); i++) {
                rocks.get(i).draw(G);
            }
            if (startingMailMoon.size() > 0) {
                startingMailMoon.get(0).draw(G);
            }
            for (int i = 0; i < mailMoons.size(); i++) {
                mailMoons.get(i).draw(G);
            }
            if (!gameDone) {
                player.draw(G);
            }
            for (int i = 0; i < booms.size(); i++) {
                if (booms.get(i).getCount() < 6) {
                    booms.get(i).draw(G);
                    booms.get(i).setCount(booms.get(i).getCount() + 1);
                } else {
                    booms.remove(i);
                    i--;
                }
            }
            // game over text
            if (gameDone) {
                G.setColor(Color.red);
                G.setFont(new Font(G.getFont().getFontName(), Font.BOLD, 150));
                G.drawString("Game Over!!!", 140, 250);

                G.setFont(new Font(G.getFont().getFontName(), Font.BOLD, 50));
                G.drawString("Score = " + player.getScore(), 460, 350);
                gCond.tick();
                gCond.draw(G);
            }
            // new level
            if (newL) {
                G.setColor(Color.red);
                G.setFont(new Font(G.getFont().getFontName(), Font.BOLD, 90));
                G.drawString("Level = " + (level + 1), 250, 250);
            }
        }
        if (Condition.getState() == mCond) {
            G.drawImage(mb, 0, 0, 1220, 680, null);
            G.drawImage(title, 475, 50, 300, 100, null);
            mCond.draw(G);
            mCond.tick();
        }
        if (Condition.getState() == hCond) {
            hCond.tick();
            G.drawImage(mb, 0, 0, 1220, 680, null);
            hCond.draw(G);
        }
        bs.show();
        G.dispose();
    }
    // setup and jframe
    @Override
    public void run() {
        initialize();
        int fps = 60;
        double timeperupdate = 1000000000 / fps;
        double delta = 0;
        long now;
        long timer = 0;
        int ticks = 0;
        long lastTime = System.nanoTime();
        while (isRun) {
            now = System.nanoTime();
            delta += (now - lastTime) / timeperupdate;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                update();
                draw();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (isRun) {
            return;
        }
        isRun = true;
        thr = new Thread(this);
        thr.start();
    }

    public synchronized void stop() {
        if (!isRun) {
            return;
        }
        isRun = false;
        try {
            thr.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    // frame and canvas
    private void setUp() {
        fr = new JFrame("Galactic Mail!!!");
        bg = new Canvas();
        bg.setPreferredSize(new Dimension(1200, 650));
        km = new Keyboard();
        mm = new Control();
        fr.addKeyListener(km);
        fr.addMouseListener(mm);
        bg.addMouseListener(mm);
        bg.setFocusable(false);
        fr.setSize(1200, 650);
        fr.add(bg);
        fr.pack();
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // returns
    public Condition getGamestate() {
        return gCond;
    }

    public Condition getMenu() {
        return mCond;
    }

    public Condition gethCond() {
        return hCond;
    }

    public Control getMouseControl() {
        return mm;
    }
    public Keyboard getKeyControl() {
        return km;
    }

    public Sound getMenuMusic() {
        return mc;
    }

    public Sound getBackgroundMusic() {
        return bc;
    }

    // main
    public static void main(String[] args) {
        GameWorld galacticMail = new GameWorld();
        galacticMail.setUp();
        galacticMail.start();
    }

}