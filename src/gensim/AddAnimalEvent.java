/*
 * This file is part of GenSim.
 *
 * GenSim is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GenSim is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GenSim.  If not, see <http://www.gnu.org/licenses/>.
 */
package gensim;

import java.util.EventObject;

/**
 *
 * @author Andrew Vitkus
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
