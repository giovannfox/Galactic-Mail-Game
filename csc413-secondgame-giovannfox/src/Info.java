package src;

import java.awt.*;

public class Info extends Condition {

    private Graphics2D gra2D;
    // buttons
    private final Rectangle playButton = new Rectangle(40, 40, 100, 40);
    private final Rectangle exitButton = new Rectangle(1000, 40, 100, 40);

    public Info(Manage man) {
        super(man);
    }

    @Override // mouse input
    public void tick() {
        // play button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >= 30 && man.getMouseControl().getXmouse() <= 110) {
            if (man.getMouseControl().getYmouse() >= 50 && man.getMouseControl().getYmouse() <= 100) {
                Condition.setState(man.getGameWorld().getGamestate());
                man.getMenuMusic().stop();
                man.getBackgroundMusic().play();
            }
        }
        // exit button
        if (man.getMouseControl().isLeftButton() && man.getMouseControl().getXmouse() >= 1100 && man.getMouseControl().getXmouse() <= 1200) {
            if (man.getMouseControl().getYmouse() >= 50 && man.getMouseControl().getYmouse() <= 100) {
                System.exit(0);
            }
        }
    }
    // display and draw buttons
    @Override
    public void draw(Graphics G) {

        Font f1 = new Font("serif", Font.BOLD, 40);
        Font f2 = new Font("serif", Font.BOLD, 35);
        Font f3 = new Font("serif", Font.BOLD, 30);

        gra2D = (Graphics2D) G;

        gra2D.setFont(f1);
        gra2D.setColor(Color.red);
        gra2D.drawString("Info", 510, 50);

        gra2D.setFont(f3);
        gra2D.drawString("Play", playButton.x + 10, playButton.y + 35);
        gra2D.draw(playButton);
        gra2D.drawString("Close", exitButton.x + 20, exitButton.y + 35);
        gra2D.draw(exitButton);

        //control
        gra2D.setFont(f1); // Sets the font
        gra2D.drawString("Controls", 470, 450);

        gra2D.setFont(f2);
        gra2D.drawString("A: Rotate Left", 470, 500);
        gra2D.drawString("D: Rotate right", 470, 550);
        gra2D.drawString("Space bar: Blast off", 470, 600);

        //info
        gra2D.setFont(f2); // Sets the font
        gra2D.drawString("1) Rocket must blast off from moon to moon to deliver mail", 40, 160);
        gra2D.drawString("2) Rocket must land on moon to get 100 points", 40, 190);
        gra2D.drawString("3) Rocket loses points every second on moon", 40, 220);
        gra2D.drawString("4) More asteroids added each level", 40, 250);

    }
}
