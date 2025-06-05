package nl.rug.oop.rts.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
/**
 * Abstract class for events.
 */
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
     */
    public abstract void apply(Army army);
}
