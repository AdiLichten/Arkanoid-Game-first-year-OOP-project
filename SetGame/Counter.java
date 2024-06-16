// this class is responsible for counting the number of objects in the game.

package SetGame;

public class Counter {
    private int counter;

    /**
     * this is the constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * this is the constructor.
     *
     * @param counter - the number we are counting from.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * the function increases the counter by the given number.
     *
     * @param number - the additional number.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * the function decreases the counter by the given number.
     *
     * @param number - the subtracted number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * the function returns the counter.
     *
     * @return int, the counter
     */
    public int getValue() {
        return this.counter;
    }
}
