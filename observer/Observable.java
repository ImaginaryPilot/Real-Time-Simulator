package nl.rug.oop.rts.observer;

import java.util.List;

/**
 * Observable interface.
 */
public interface Observable {
    /**
     * Gets the list of observers.
     *
     * @return The list of observers.
     */
    List<Observer> getObservers();

    /**
     * Updates all the observers.
     */
    default void updateAllObservers() {
        for (Observer observer : getObservers()) {
            observer.update();
        }
    }
}
