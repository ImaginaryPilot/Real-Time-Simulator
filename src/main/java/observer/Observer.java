package nl.rug.oop.rts.observer;

/**
 * Observer interface.
 */
public interface Observer {
    /**
     * Updates the observer.
     */
    void update();

    /**
     * Observes an observable.
     *
     * @param observable The observable to observe.
     */
    default void observe(Observable observable) {
        observable.getObservers().add(this);
    }
}
