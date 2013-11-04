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

import gensim.genes.BarredFeatherGene;
import gensim.genes.BlackFeatherGene;
import gensim.genes.BredaCombGene;
import gensim.genes.ColumbianFeatherGene;
import gensim.genes.CreeperLegGene;
import gensim.genes.FrizzleFeatherGene;
import gensim.genes.Gene;
import gensim.genes.ShellColorGene;
import java.util.Random;

/**
 *
 * @author Andrew Vitkus
 */
public class Chicken implements Animal {

    private final int geneCount = 7;
    private Gene[] genes = new Gene[geneCount];
    private String[] phenotype = new String[7];
    private Chicken[] parents = new Chicken[2];

    public Chicken(String[] genotype) {
        genes[0] = new BarredFeatherGene(genotype[0]);
        genes[1] = new FrizzleFeatherGene(genotype[1]);
        genes[2] = new BlackFeatherGene(genotype[2]);
        genes[3] = new ColumbianFeatherGene(genotype[3]);
        genes[4] = new CreeperLegGene(genotype[4]);
        genes[5] = new BredaCombGene(genotype[5]);
        genes[6] = new ShellColorGene(genotype[6]);

        buildPhenotype();
    }

    public static String[] getTitles() {
        String[] titles = new String[7];

        titles[0] = "Sex";
        titles[1] = "Feather Style";
        titles[2] = "Feather Decoration";
        titles[3] = "Feather Color";
        titles[4] = "Leg Style";
        titles[5] = "Breda Comb";
        titles[6] = "Egg Color";

        return titles;
    }

    @Override
    public Animal breed(Animal a) {
        if (a instanceof Chicken) {
            Chicken mate = (Chicken) a;
            if (isMale() == !mate.isMale()) {
                String[] child = new String[geneCount];
                do {
                    char[] a1 = getRandomAlleles();
                    char[] a2 = ((Chicken) mate).getRandomAlleles();

                    for (int i = 0; i < geneCount; i++) {
                        child[i] = "" + a1[i] + a2[i];
                    }
                } while (child[4].equals("CC"));

                Chicken chick = new Chicken(child);
                chick.parents[0] = this;
                chick.parents[1] = mate;
                return chick;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String[] getPhenotypes() {
        return phenotype;
    }

    @Override
    public String[] getGenotypes() {
        String[] genotypes = new String[geneCount];

        for (int i = 0; i < geneCount; i++) {
            genotypes[i] = genes[i].getGenotype();
        }

        return genotypes;
    }

    @Override
    public String[] getGeneList() {
        String[] geneList = new String[geneCount];

        for (int i = 0; i < geneCount; i++) {
            geneList[i] = genes[i].getGeneName();
        }

        return geneList;

    }

    private char[] getRandomAlleles() {
        char[] alleles = new char[geneCount];

        Random rand = new Random();
        for (int i = 0; i < geneCount; i++) {
            alleles[i] = genes[i].getGenotype().charAt(rand.nextInt(2));
        }

        return alleles;
    }

    @Override
    public boolean isMale() {
        if (genes[0].getGenotype().contains("W")) {
            return false;
        }
        return true;
    }

    private void buildPhenotype() {
        phenotype[0] = isMale() ? "Male" : "Female";
        phenotype[1] = genes[1].getPhenotype();
        phenotype[2] = genes[0].getPhenotype();
        if (genes[2].getPhenotype().equalsIgnoreCase("Black")) {
            phenotype[3] = "Black";
        } else if (genes[3].getPhenotype().equalsIgnoreCase("Columbian")) {
            phenotype[3] = "Columbian";
        } else {
            phenotype[3] = "Wheaten";
        }
        phenotype[4] = genes[4].getPhenotype();
        phenotype[5] = genes[5].getPhenotype();
        phenotype[6] = genes[6].getPhenotype();
    }

    @Override
    public boolean isAlive() {
        return (!genes[4].getPhenotype().equalsIgnoreCase("Dead"));
    }

    @Override
    public Animal[] getParents() {
        return parents;
    }
}
