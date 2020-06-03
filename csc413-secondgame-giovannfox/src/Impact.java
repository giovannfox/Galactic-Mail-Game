package src;

import java.util.ArrayList;

public class Impact {

    // sounds for impact and when landing on moon
    private Sound expl;
    private Sound succ;

    // player landing on moon
    public void playerMoon(Rocket r, ArrayList<MailMoon> mm, Keyboard keyboard) {
        for (int i = 0; i < mm.size(); i++) {
            if (r.getRec().getBounds2D().intersects(mm.get(i).getRec().getBounds2D())
                    && r.getRec().getBounds2D().getMinX() - 5 > mm.get(i).getRec().getBounds2D().getMinX()
                    && r.getRec().getBounds2D().getMaxX() + 5 < mm.get(i).getRec().getBounds2D().getMaxX()
                    && r.getRec().getBounds2D().getMinY() - 5 > mm.get(i).getRec().getBounds2D().getMinY()
                    && r.getRec().getBounds2D().getMaxY() + 5 < mm.get(i).getRec().getBounds2D().getMaxY()) {

                // adds points for each good landing
                // rocket animation
                // new speed each time rocket leaves moon
                if (!mm.get(i).getOn()) {
                    r.setIsShot(false);
                    succ = new Sound("Resources/Bonus.wav");
                    succ.playonce();
                    if (!mm.get(i).getStartMoon()) {
                        r.setScore(r.getScore() + 100);
                    }
                    r.setImage("-off");
                    r.setOnMoon(true);
                }
                mm.get(i).setOn(true);
                // player loses points for each second they stay on moon
                if (r.getScore() > 0 && !mm.get(i).getStartMoon()) {
                    r.setScore(r.getScore() - 1);
                }
            }
            //once rocket leaves moon delete moon
            if (mm.get(i).getOn() && !r.getRec().getBounds2D().intersects(mm.get(i).getRec().getBounds2D())) {
                r.setOnMoon(false);
                mm.remove(i);
                i--;
            }
        }
    }

    // collision of rocket and rock
    public boolean playerAsteroid(Rocket r, ArrayList<Rock> b, Keyboard keyboard, ArrayList<Boom> x) {
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i).getRec().getBounds2D().intersects(r.getRec().getBounds2D())
                    && !r.getOnMoon()) {
                expl = new Sound("Resources/Explosion.wav");
                expl.playonce();
                x.add(new Boom(r.getCenterX() - 24, r.getCenterY() - 24, 90, 90, "Explosion.gif"));
                return true;
            }
        }
        return false;
    }

}
