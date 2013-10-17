/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gensim;

import java.util.EventListener;

/**
 *
 * @author Andrew
 */
public interface AddAnimalEventListener extends EventListener {
    public void animalAdded(AddAnimalEvent e);
}
