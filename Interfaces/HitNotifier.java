// The interface represents a hit notifier.

package Interfaces;

public interface HitNotifier {

    /**
     * the function adds the listener to hit events.
     *
     * @param hl - the listener.
     */
    void addHitListener(HitListener hl);

    /**
     * the function removes the listener from hit events.
     *
     * @param hl - the listener.
     */
    void removeHitListener(HitListener hl);
}
