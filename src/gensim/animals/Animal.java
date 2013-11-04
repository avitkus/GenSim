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

package gensim.animals;

import java.io.Serializable;

/**
 *
 * @author Andrew Vitkus
 */
public interface Animal extends Serializable {

    public Animal breed(Animal mate);

    public String[] getPhenotypes();

    public String[] getGenotypes();

    public String[] getGeneList();

    public boolean isMale();

    public boolean isAlive();

    public Animal[] getParents();
}
