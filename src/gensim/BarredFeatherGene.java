package gensim;

/**
 *
 * @author Andrew
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
