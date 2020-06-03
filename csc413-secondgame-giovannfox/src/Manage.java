package src;

public class Manage {
    // game world
    private GameWorld gameWorld;
    
    // game world initilized
    public Manage(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    // return game world
    public GameWorld getGameWorld() {
        return gameWorld;
    }

    // set game world object
    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    // return menu music
    public Sound getMenuMusic() {
        return gameWorld.getMenuMusic();
    }

    // return background music
    public Sound getBackgroundMusic() {
        return gameWorld.getBackgroundMusic();
    }

    // return mouse control from game world
    public Control getMouseControl() {
        return gameWorld.getMouseControl();
    }

    // return key control from game world
    public Keyboard getKeyControl() {
        return gameWorld.getKeyControl();
    }

    // reinitialize and call run
    public void reset() {
        gameWorld.run();
    }

}
