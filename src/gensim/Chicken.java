package gensim;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Andrew
 */
public class Chicken implements Animal, java.io.Serializable {

    private final int geneCount = 6;
    private Gene[] genes = new Gene[geneCount];
    private String[] phenotype = new String[6];

    public Chicken(String[] genotype) {
        genes[0] = new BarredFeatherGene(genotype[0]);
        genes[1] = new FrizzleFeatherGene(genotype[1]);
        genes[2] = new WhiteFeatherGene(genotype[2]);
        genes[3] = new ColoredFeatherGene(genotype[3]);
        genes[4] = new CreeperLegGene(genotype[4]);
        genes[5] = new ShellColorGene(genotype[5]);

        buildPhenotype();
    }

    public static String[] getTitles() {
        String[] titles = new String[6];

        titles[0] = "Sex";
        titles[1] = "Feather Style";
        titles[2] = "Feather Decoration";
        titles[3] = "Feather Color";
        titles[4] = "Leg Style";
        titles[5] = "Egg Color";

        return titles;
    }

    @Override
    public Animal breed(Animal mate) {
        if (mate instanceof Chicken) {
            if (isMale() == !mate.isMale()) {
                String[] child = new String[geneCount];
                do {
                    char[] a1 = getRandomAlleles();
                    char[] a2 = ((Chicken) mate).getRandomAlleles();

                    for (int i = 0; i < geneCount; i++) {
                        child[i] = "" + a1[i] + a2[i];
                    }
                } while (child[4].equals("CC"));

                return new Chicken(child);
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

        for (int i = 0; i < geneCount; i++) {
            alleles[i] = genes[i].getGenotype().charAt(ThreadLocalRandom.current().nextInt(2));
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
        if (genes[2].getPhenotype().equalsIgnoreCase("White")) {
            phenotype[3] = "White";
        } else if (genes[3].getPhenotype().equalsIgnoreCase("White")) {
            phenotype[3] = "White";
        } else {
            phenotype[3] = "Colored";
        }
        phenotype[4] = genes[4].getPhenotype();
        phenotype[5] = genes[5].getPhenotype();
    }

    @Override
    public boolean isAlive() {
        return (!genes[4].getPhenotype().equalsIgnoreCase("Dead"));
    }
}
