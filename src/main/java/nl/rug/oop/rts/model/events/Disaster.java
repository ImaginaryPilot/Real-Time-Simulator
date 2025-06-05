package nl.rug.oop.rts.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.model.army.Army;

import java.util.Random;

@Setter
@Getter
/**
 * Class for natural disasters.
 */
public class Disaster extends Event{
    /**
     * Units removed by the event.
     */
    private final Random random = new Random();
    /**
     * Units removed by the event.
     */
    private int unitsRemoved;

    /**
     * Constructor for the Disaster class.
     */
    public Disaster(){
        super("Tornado", "A tornado strikes the army, causing massive casualties.");
    }

    /**
     * Applies the disaster to the army.
     *
     * @param army the army that suffers the effects.
     */
    @Override
    public void apply(Army army){
        unitsRemoved = 5 + random.nextInt(15);
        for(int i = 0; i < unitsRemoved && !army.getUnits().isEmpty(); i++){
            army.getUnits().remove(random.nextInt(army.getUnits().size()));
        }

        if(army.getUnits().size() <= 0){

        }
    }
}
