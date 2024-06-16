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

public class Level3 implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 2;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 100;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private List<Point> startingPoints;

    /**
     * this is the constructor.
     */
    public Level3() {
        this.blocks = new ArrayList<>();
        this.startingPoints = new ArrayList<>();
        this.velocities = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity1 = new Velocity(-3, -10);
        Velocity velocity2 = new Velocity(3, -10);
        velocities.add(velocity1);
        velocities.add(velocity2);
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green.darker().darker(), "Green 3");
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.white, Color.blue, Color.yellow, Color.red, Color.gray};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 6; j++) {
                Point startingPoint = new Point(GameLevel.WIDTH - GameLevel.BORDER - 50 - j * 50, 300 - 20 * i);
                Block block = new Block(new Rectangle(startingPoint, 50, 20), colors[i], Color.BLACK);
                this.blocks.add(block);
            }
        }
        for (int i = 0; i < 11; i++) {
            Point startingPoint = new Point(GameLevel.WIDTH - GameLevel.BORDER - 50 - i * 50, 220);
            Block block = new Block(new Rectangle(startingPoint, 50, 20), colors[4], Color.BLACK);
            this.blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Point> centerPoints() {
        Point startingPoint = new Point((double) GameLevel.WIDTH / 2, (double) GameLevel.HEIGHT - 100);
        this.startingPoints.add(startingPoint);
        return startingPoints;
    }
}
