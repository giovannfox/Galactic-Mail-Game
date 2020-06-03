package src;

import java.awt.*;

public class PlayCondition extends Condition {
    private final Rectangle menuButton =
            new Rectangle(550, 350, 150, 50);

    private final Rectangle exitButton =
            new Rectangle(550, 450, 150, 50);

    PlayCondition(Manage man) {
        super(man);
    }

    // draw buttons and messages
    @Override
    public void draw(Graphics G) {
        Graphics2D graph2D = (Graphics2D) G;

        Font f2 = new Font("Serif", Font.BOLD, 30);
        graph2D.setFont(f2);

        graph2D.drawString("Restart", menuButton.x + 5, menuButton.y + 35);
        graph2D.draw(menuButton);

        graph2D.drawString("Exit", exitButton.x + 45, exitButton.y + 35);
        graph2D.draw(exitButton);
    }

    @Override
    public void tick() {
        // start screen button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >=
                540 && man.getMouseControl().getXmouse() <= 690) {
            if (man.getMouseControl().getYmouse() >=
                    375 && man.getMouseControl().getYmouse() <= 425) {
                man.getBackgroundMusic().stop();
                man.reset();
            }
        }
        // exit screen button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >=
                540 && man.getMouseControl().getXmouse() <= 690) {
            if (man.getMouseControl().getYmouse() >=
                    475 && man.getMouseControl().getYmouse() <= 525) {
                System.exit(0);
            }
        }
    }

}
