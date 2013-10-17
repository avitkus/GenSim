package gensim;

import java.util.EventObject;

/**
 *
 * @author Andrew
 */
public class AddAnimalEvent extends EventObject {
    private Animal a;
    
    AddAnimalEvent(Object source) {
        super(source);
        this.a = null;
    }
    
    AddAnimalEvent(Animal a, Object source) {
        super(source);
        this.a = a;
    }
    
    public Animal getAnimal() {
        return a;
    }
}
