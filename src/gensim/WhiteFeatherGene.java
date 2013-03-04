package gensim;

/**
 *
 * @author Andrew
 */
public class WhiteFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "WW";
        genotypes[1] = "Ww";
        genotypes[2] = "ww";

        phenotypes = new String[3];
        phenotypes[0] = "White";
        phenotypes[1] = "White";
        phenotypes[2] = "Colored";
    }

    public WhiteFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "WW":
                phenotype = "White";
                break;
            case "Ww":
            case "wW":
                phenotype = "White";
                break;
            case "ww":
                phenotype = "Colored";
                break;
            default:
                System.err.println("Invalid genotype set for white feathers!");
        }
    }

    @Override
    public String getGeneName() {
        return "White Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "White Feathers";
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
