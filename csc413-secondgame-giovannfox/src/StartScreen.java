package src;

import java.awt.*;

public class StartScreen extends Condition {

    public StartScreen(Manage man) {
        super(man);
        man.getMenuMusic().play();
    }

    private final Rectangle playButton =
            new Rectangle(570, 175, 200, 40);
    private final Rectangle infoButton =
            new Rectangle(570, 275, 200, 40);
    private final Rectangle closeButton =
            new Rectangle(570, 375, 200, 40);

    private Graphics2D graph2D;


    // draws button and display message
    @Override
    public void draw(Graphics G) {
        graph2D = (Graphics2D) G;
        Font f2 = new Font("serif", Font.BOLD, 50);
        graph2D.setFont(f2);
        graph2D.setColor(Color.red);
        graph2D.drawString("Start", playButton.x + 15, playButton.y + 33);
        graph2D.draw(playButton);

        graph2D.drawString("Info", infoButton.x + 14, infoButton.y + 33);
        graph2D.draw(infoButton);

        graph2D.drawString("Close", closeButton.x + 10, closeButton.y + 33);
        graph2D.draw(closeButton);
    }

    @Override
    public void tick() {
        // play button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >=
                580 && man.getMouseControl().getXmouse() <= 680) {
            if (man.getMouseControl().getYmouse() >=
                    195 && man.getMouseControl().getYmouse() <= 245) {
                Condition.setState(man.getGameWorld().getGamestate());
                man.getMenuMusic().stop();
                man.getBackgroundMusic().play();
            }
        }
        // info button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >=
                580 && man.getMouseControl().getXmouse() <= 680) {
            if (man.getMouseControl().getYmouse() >=
                    265 && man.getMouseControl().getYmouse() <= 325) {
                Condition.setState(man.getGameWorld().gethCond());
            }
        }
        // exit button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >= 580 && man.getMouseControl().getXmouse() <= 680) {
            if (man.getMouseControl().getYmouse() >= 385 && man.getMouseControl().getYmouse() <= 435) {
                System.exit(0);
            }
        }
    }
}
