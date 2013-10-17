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

import java.util.ArrayList;

/**
 *
 * @author Andrew Vitkus
 */
public abstract class Gene implements java.io.Serializable {

    static protected String[] genotypes;
    static protected String[] phenotypes;

    public abstract String getGeneName();

    public abstract String getEffectedTrait();

    public abstract String getGenotype();

    public abstract String getPhenotype();

    public static String[] getGenotypeForPhenotype(String phenotype) {
        int i = 0;
        ArrayList<String> alleles = new ArrayList<>();
        for (; i < phenotypes.length; i++) {
            if (phenotypes[i].equals(phenotype)) {
                alleles.add(genotypes[i]);
            }
        }

        return (String[]) alleles.toArray();
    }

    public static String getPhenotypeForGenotype(String genotype) {
        for (int i = 0; i < genotypes.length; i++) {
            if (genotypes[i].equals(genotype)) {
                return phenotypes[i];
            }
        }
        return "";
    }
}
