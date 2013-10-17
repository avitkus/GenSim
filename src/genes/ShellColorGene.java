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
package genes;

/**
 *
 * @author Andrew Vitkus
 */
public class ShellColorGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "BB";
        genotypes[1] = "Bb";
        genotypes[2] = "bb";

        phenotypes = new String[3];
        phenotypes[0] = "Blue";
        phenotypes[1] = "Blue";
        phenotypes[2] = "White";
    }

    public ShellColorGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "BB":
                phenotype = "Blue";
                break;
            case "Bb":
            case "bB":
                phenotype = "Blue";
                break;
            case "bb":
                phenotype = "White";
                break;
            default:
            //System.err.println("Invalid genotype set for shell color!");
        }
    }

    @Override
    public String getGeneName() {
        return "Egg Color";
    }

    @Override
    public String getEffectedTrait() {
        return "Egg Color";
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
