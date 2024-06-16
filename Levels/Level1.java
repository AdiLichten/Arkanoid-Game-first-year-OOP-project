
package Levels;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import SetGame.GameLevel;
import Sprites.Background;
import Sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level1 implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 1;
    private static final int DY = -7;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 100;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private List<Point> startingPoints;

    /**
     * this is the constructor.
     */
    public Level1() {
        this.velocities = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.startingPoints = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        velocities.add(new Velocity(0, DY));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.BLACK, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point((double) GameLevel.WIDTH / 2 - 10,
                (double) GameLevel.HEIGHT / 4 - 10), 20, 20), Color.RED, Color.RED);
        this.blocks.add(block);
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public List<Point> centerPoints() {
        Point startingPoint = new Point((double) GameLevel.WIDTH / 2, (double) GameLevel.HEIGHT - 100);
        this.startingPoints.add(startingPoint);
        return startingPoints;
    }
}
