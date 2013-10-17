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
public class BarredFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[5];
        genotypes[0] = "ZZ";
        genotypes[1] = "Zz";
        genotypes[2] = "zz";
        genotypes[3] = "ZW";
        genotypes[4] = "zW";

        phenotypes = new String[5];
        phenotypes[0] = "Barred";
        phenotypes[1] = "Barred";
        phenotypes[2] = "Solid";
        phenotypes[3] = "Barred";
        phenotypes[4] = "Solid";
    }

    public BarredFeatherGene(String genotype) {
        this.genotype = genotype;
        if (genotype.contains("Z")) {
            phenotype = "Barred";
        } else {
            phenotype = "Solid";
        }
    }

    @Override
    public String getGeneName() {
        return "Barred Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "Barred Feathers";
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
