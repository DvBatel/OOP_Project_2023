package ConstsAndMethods;

/**
 * The type Counter.
 */
public class Counter {

    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.counter = Const.ZERO;
    }

    /**
     * Instantiates a new Counter.
     * @param number the number to start from
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.counter;
    }
}
