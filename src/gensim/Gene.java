package gensim;

import java.util.ArrayList;

/**
 *
 * @author Andrew
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
}
