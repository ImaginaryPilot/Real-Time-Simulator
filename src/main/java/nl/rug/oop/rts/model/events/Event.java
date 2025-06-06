package nl.rug.oop.rts.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;

/**
 * Abstract class for events.
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Event {
    /**
     * The name of the event.
     */
    private String name;
    /**
     * The description of the event.
     */
    private String description;

    /**
     * Abstract function to apply the event to an army.
     *
     * @param army the army
     */
    public abstract void apply(Army army);
}
