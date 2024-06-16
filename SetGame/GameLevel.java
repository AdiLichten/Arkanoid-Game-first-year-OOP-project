// The GameLevel class is in charge of the game itself.
// It holds the sprites and the collidables, and will be in charge of the animation.
package SetGame;

import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Interfaces.Sprite;

import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreTrackingListener;
import Screens.CountdownAnimation;
import Screens.PauseScreen;
import Sprites.Block;
import Sprites.Ball;
import Sprites.Paddle;
import Sprites.ScoreIndicator;
import Sprites.LivesIndicator;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.List;
import java.awt.Color;

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private static final int RADIUS = 7;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER = 25;
    private static final int SECONDS = 3;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * this is a constructor that initializes the game.
     *
     * @param levelInformation - the level.
     * @param ar               - the animation runner.
     * @param score            - the score counter.
     * @param lives            - the lives.
     * @param blocksCounter    - the blocks counter.
     * @param ballsCounter     - the balls counter.
     * @param gui              - the gui.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, Counter score,
                     Counter lives, Counter blocksCounter, Counter ballsCounter, GUI gui) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = blocksCounter;
        this.ballCounter = ballsCounter;
        this.score = score;
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.runner = ar;
        this.keyboard = this.gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.lives = lives;
    }

    /**
     * the function adds a collidable to the list.
     *
     * @param c - the new collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * the function adds a sprite to the list.
     *
     * @param s - the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    private void setBlocks(List<Block> blocks) {
        for (Block block : blocks) {
            this.blockCounter.increase(1);
            block.addBlockToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrackingListener);
        }
    }

    /**
     * the function adds a ball to the game.
     */
    public void setBalls() {
        this.ballCounter.decrease(this.ballCounter.getValue());
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(levelInformation.centerPoints().get(i).getX(),
                    levelInformation.centerPoints().get(i).getY(), RADIUS, Color.WHITE);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * the function adds the borders to the game.
     */
    private void addBorders() {
        Block[] borders = new Block[4];
        borders[0] = new Block(new Rectangle(new Point(0, BORDER), BORDER, HEIGHT - BORDER), Color.GRAY,
                Color.GRAY);
        borders[1] = new Block(new Rectangle(new Point(BORDER, HEIGHT), WIDTH - BORDER, BORDER),
                Color.GRAY, Color.GRAY);
        borders[2] = new Block(new Rectangle(new Point(WIDTH - BORDER, 0), BORDER, HEIGHT),
                Color.GRAY, Color.GRAY);
        borders[3] = new Block(new Rectangle(new Point(0, 0), WIDTH - BORDER, 2 * BORDER),
                Color.GRAY, Color.GRAY);
        for (Block border : borders) {
            border.addBlockToGame(this);
        }
        borders[1].addHitListener(this.ballRemover);
    }

    /**
     * the function adds the paddle to the game.
     */
    private void setPaddle() {
        Paddle paddle = new Paddle(this.keyboard, this.levelInformation.paddleWidth(), Color.ORANGE,
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * the function removes a collidable from the game.
     *
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * the function removes a sprite from the sprites.
     *
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * the function initialize a new game: create the Blocks and Ball and Paddle and add them to the game.
     */
    public void initialize() {
        addSprite(this.levelInformation.getBackground());
        setBlocks(this.levelInformation.blocks());
        setPaddle();
        setBalls();
        addBorders();
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, this);
        LivesIndicator livesIndicator = new LivesIndicator(lives, this);
    }

    /**
     * the function runs the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(SECONDS, SECONDS, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE.darker().darker());
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the function returns the game environment.
     *
     * @return game environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * the function returns the level information.
     *
     * @return level information.
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }
}
