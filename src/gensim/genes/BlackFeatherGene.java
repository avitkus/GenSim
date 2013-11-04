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
package gensim.genes;

/**
 *
 * @author Andrew Vitkus
 */
public class BlackFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "EE";
        genotypes[1] = "Ee";
        genotypes[2] = "ee";

        phenotypes = new String[3];
        phenotypes[0] = "Black";
        phenotypes[1] = "Black";
        phenotypes[2] = "Wheaten";
    }

    public BlackFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "EE":
                phenotype = "Black";
                break;
            case "Ee":
            case "eE":
                phenotype = "Black";
                break;
            case "ee":
                phenotype = "Wheaten";
                break;
            default:
            //System.err.println("Invalid genotype set for black feathers!");
        }
    }

    @Override
    public String getGeneName() {
        return "Black Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "Black Feathers";
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
