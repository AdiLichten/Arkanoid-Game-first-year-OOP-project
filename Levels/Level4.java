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

public class Level4 implements LevelInformation {
    private static final int NUMBER_OF_BALLS = 3;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 100;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private List<Point> startingPoints;

    /**
     * this is the constructor.
     */
    public Level4() {
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
        Velocity velocity1 = new Velocity(-5, -8);
        Velocity velocity2 = new Velocity(0, -5);
        Velocity velocity3 = new Velocity(5, -8);
        velocities.add(velocity1);
        velocities.add(velocity2);
        velocities.add(velocity3);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Color newBlue = new Color(50, 130, 246);
        return new Background(newBlue, "Final Four");
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.cyan, Color.pink, Color.white, Color.green, Color.yellow, Color.red, Color.gray};
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                Point startingPoint = new Point(GameLevel.WIDTH - GameLevel.BORDER - 50 - j * 50, 300 - 20 * i);
                Block block = new Block(new Rectangle(startingPoint, 50, 20), colors[i], Color.BLACK);
                this.blocks.add(block);
            }
        }
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
