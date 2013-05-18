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

/**
 *
 * @author Andrew Vitkus
 */
public class FrizzleFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "FF";
        genotypes[1] = "Ff";
        genotypes[2] = "ff";

        phenotypes = new String[3];
        phenotypes[0] = "Normal";
        phenotypes[1] = "Frizzle";
        phenotypes[2] = "Curly";
    }

    public FrizzleFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "FF":
                phenotype = "Normal";
                break;
            case "Ff":
            case "fF":
                phenotype = "Frizzle";
                break;
            case "ff":
                phenotype = "Curly";
                break;
            default:
                System.err.println("Invalid genotype set for frizzle feather!");
        }
    }

    @Override
    public String getGeneName() {
        return "Frizzle Feather";
    }

    @Override
    public String getEffectedTrait() {
        return "Frizzle Feather";
    }

    @Override
    public String getGenotype() {
        return genotype;
    }

    @Override
    public String getPhenotype() {
        return phenotype;
    }
}
