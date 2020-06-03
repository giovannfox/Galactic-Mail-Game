package src;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Keyboard implements KeyListener {
    // control keys
    private boolean left;
    private boolean right;
    private boolean shoot;

    // stores key in keyboard
    private final boolean[] k;

    // constructor
    public Keyboard() {
        k = new boolean[256];
    }

    // updates key value each tick within the updated loop
    public void tick() {
        left = k[KeyEvent.VK_A];
        right = k[KeyEvent.VK_D];
        shoot = k[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        k[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        k[ke.getKeyCode()] = false;
    }
    // getters to move the rocket

    // set launch key to false when rocket is flying
    public void setShoot() {
        shoot = false;
    }

    public boolean getR() {
        return right;
    }

    public boolean getL() {
        return left;
    }

    public boolean getShoot(){
        return shoot;
    }

}
