package gensim;

/**
 *
 * @author Andrew
 */
public class ColumbianFeatherGene extends Gene {

    String phenotype;
    String genotype;

    static {
        genotypes = new String[3];
        genotypes[0] = "CC";
        genotypes[1] = "Cc";
        genotypes[2] = "cc";

        phenotypes = new String[3];
        phenotypes[0] = "Columbian";
        phenotypes[1] = "Columbian";
        phenotypes[2] = "Wheaten";
    }

    public ColumbianFeatherGene(String genotype) {
        this.genotype = genotype;
        switch (genotype) {
            case "CC":
                phenotype = "Columbian";
                break;
            case "Cc":
            case "cC":
                phenotype = "Columbian";
                break;
            case "cc":
                phenotype = "Wheaten";
                break;
            default:
                System.err.println("Invalid genotype set for columbian feathers!");
        }
    }

    @Override
    public String getGeneName() {
        return "Columbian Feathers";
    }

    @Override
    public String getEffectedTrait() {
        return "Columbian Feathers";
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
