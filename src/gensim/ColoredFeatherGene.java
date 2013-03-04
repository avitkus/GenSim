package gensim;

/**
 *
 * @author Andrew
 */
public class ColoredFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "CC";
        genotypes[1] = "Cc";
        genotypes[2] = "cc";

        phenotypes = new String[3];
        phenotypes[0] = "Colored";
        phenotypes[1] = "Colored";
        phenotypes[2] = "White";
    }

    public ColoredFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "CC":
                phenotype = "Colored";
                break;
            case "Cc":
            case "cC":
                phenotype = "Colored";
                break;
            case "cc":
                phenotype = "White";
                break;
            default:
                System.err.println("Invalid genotype set for colored feathers!");
        }
    }

    @Override
    public String getGeneName() {
        return "Colored Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "Colored Feathers";
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
