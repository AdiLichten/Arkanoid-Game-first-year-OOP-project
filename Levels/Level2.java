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

public class Level2 implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 10;
    private static final int PADDLE_SPEED = 1;
    private static final int PADDLE_WIDTH = 600;
    private List<Velocity> velocities;
    private List<Block> blocks;
    private List<Point> startingPoints;

    /**
     * this is the constructor.
     */
    public Level2() {
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
        for (int i = 1; i <= numberOfBalls() / 2; i++) {
            Velocity velocity = new Velocity(-7 + i, i * -3);
            this.velocities.add(velocity);
        }
        for (int i = 1; i <= numberOfBalls() / 2; i++) {
            Velocity velocity = new Velocity(7 - i, i * -3);
            this.velocities.add(velocity);
        }
        return this.velocities;
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.WHITE, this.levelName());
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow, Color.green,
                Color.green, Color.green, Color.blue, Color.blue, Color.pink, Color.pink, Color.cyan, Color.cyan};
        for (int i = 0; i < colors.length; i++) {
            Point upperLeft = new Point(GameLevel.BORDER + i * 50, (double) GameLevel.HEIGHT / 2);
            Rectangle rectangle = new Rectangle(upperLeft, 50, 20);
            Block block = new Block(rectangle, colors[i], Color.BLACK);
            this.blocks.add(block);
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
