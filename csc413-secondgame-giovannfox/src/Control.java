package src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Control implements MouseListener {

    // checks if left button is pressed
    private boolean leftButton;
    // x and y coordinates of mouth cursor
    private int Xmouse;
    private int Ymouse;

    @Override
    public void mousePressed(MouseEvent M) {
        // mouse cursor positions
        Xmouse = M.getX();
        Ymouse = M.getY();
        if (M.getButton() == MouseEvent.BUTTON1) {
            leftButton = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent M) {
    }

    @Override
    public void mouseReleased(MouseEvent M) {
        if (M.getButton() == MouseEvent.BUTTON1) {
            leftButton = false;
        } 
    }

    @Override
    public void mouseEntered(MouseEvent M) {
    }

    @Override
    public void mouseExited(MouseEvent M) {
    }

    public boolean isLeftButton() {
        return leftButton;
    }

    // y position of cursor
    public int getYmouse() {
        return Ymouse;
    }

    // x position of cursor
    public int getXmouse() {
        return Xmouse;
    }

}
